package br.com.framework.post.controllers;

import br.com.framework.post.models.Album;
import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;
import br.com.framework.post.repositories.IAlbumRepository;
import br.com.framework.post.repositories.ICommentRepository;
import br.com.framework.post.repositories.IPostRepository;
import br.com.framework.post.security.services.AuthenticationService;
import br.com.framework.post.transforms.dto.*;
import br.com.framework.post.transforms.form.AlbumCreateForm;
import br.com.framework.post.transforms.form.CommentCreateForm;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private IAlbumRepository albumRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    @Cacheable(value = "album-list")
    public Page<AlbumDto> list(Pageable pageable){
        Page<Album> albums = this.albumRepository.findAll(pageable);
        return AlbumDto.converter(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDetailDto> find(@PathVariable Long id){
        Optional<Album> album = this.albumRepository.findById(id);
        return album.map(value -> ResponseEntity.ok(new AlbumDetailDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "album-list", allEntries = true)
    public ResponseEntity<?> created(@RequestBody @Valid AlbumCreateForm createForm){
        User user = this.authenticationService.getUser();

        Album album = createForm.converter(user);
        this.albumRepository.save(album);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AlbumDto(album));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "album-list", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){
        User user = this.authenticationService.getUser();

        Optional<Album> album = albumRepository.findById(id);
        if (!album.isPresent()){
            return ResponseEntity.notFound().build();
        }else if (!album.get().getAuthor().equals(user)){
            return ResponseEntity.badRequest()
                    .body(new FormErrorDto("user-invalid", "You cannot delete a album that is not yours"));
        }

        this.albumRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/add/post/{idPost}")
    @Transactional
    public ResponseEntity<?> addPost(@PathVariable Long id, @PathVariable Long idPost){
        return this.managerAlbum(id, idPost, 1);
    }

    @DeleteMapping("{id}/remove/post/{idPost}")
    @Transactional
    public ResponseEntity<?> removePost(@PathVariable Long id, @PathVariable Long idPost){
        return this.managerAlbum(id, idPost, 2);
    }

    private ResponseEntity<?> validAlbumInformation(
            Optional<Album> optionalAlbum, Optional<Post> optionalPost, User user){

        if (!optionalAlbum.isPresent()){
            return ResponseEntity.badRequest()
                    .body(new FormErrorDto("album", "This album not exist"));
        }

        if (!optionalPost.isPresent()){
            return ResponseEntity.badRequest()
                    .body(new FormErrorDto("album", "This post not exist"));
        }

        if (!optionalAlbum.get().getAuthor().equals(user)){
            return ResponseEntity.badRequest()
                    .body(new FormErrorDto("user-invalid", "You cannot add a poster in album that is not yours"));
        }
        return null;
    }

    private ResponseEntity<?> managerAlbum(Long id, Long idPost,int action){
        Optional<Album> optionalAlbum = this.albumRepository.findById(id);
        Optional<Post> optionalPost = this.postRepository.findById(idPost);

        ResponseEntity<?> responseEntity = validAlbumInformation(optionalAlbum, optionalPost, this.authenticationService.getUser());

        if (responseEntity != null){
            return responseEntity;
        }

        Album album = optionalAlbum.get();

        switch (action){
            case 1:
                album.getPosts().add(optionalPost.get());
                break;
            case 2:
                album.getPosts().remove(optionalPost.get());
                break;
        }

        return ResponseEntity.ok(new AlbumDetailDto(album));
    }
}
