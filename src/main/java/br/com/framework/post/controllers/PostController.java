package br.com.framework.post.controllers;

import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;
import br.com.framework.post.repositories.IPostRepository;
import br.com.framework.post.security.services.AuthenticationService;
import br.com.framework.post.transforms.dto.FormErrorDto;
import br.com.framework.post.transforms.dto.PostDetailDto;
import br.com.framework.post.transforms.dto.PostDto;
import br.com.framework.post.transforms.form.PostCreateForm;
import br.com.framework.post.transforms.form.PostUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    @Cacheable(value = "post-list")
    public List<PostDto> list(){
        List<Post> posts = this.postRepository.findByOrderByIdDesc();
        return PostDto.converter(posts);
    }

    @GetMapping("/pagination")
    @Cacheable(value = "post-list")
    public Page<PostDto> list(Pageable pageable){
        Page<Post> posts = this.postRepository.findAll(pageable);
        return PostDto.converterPagination(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailDto> find(@PathVariable Long id){
        Optional<Post> post = this.postRepository.findById(id);
        return post.map(value -> ResponseEntity.ok(new PostDetailDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "post-list", allEntries = true)
    public ResponseEntity<PostDto> created(@RequestBody @Valid PostCreateForm createForm, UriComponentsBuilder uriComponentsBuilder){
        User user = this.authenticationService.getUser();

        Post post = createForm.converter(user);
        this.postRepository.save(post);

        URI uri = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
        return  ResponseEntity.created(uri).body(new PostDto((post)));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "post-list", allEntries = true)
    public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody @Valid PostUpdateForm postUpdateForm){
        Optional<Post> post = this.postRepository.findById(id);

        return post.map(value -> ResponseEntity.ok(new PostDto(postUpdateForm.update(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "post-list", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){
        User user = this.authenticationService.getUser();

        Optional<Post> post = this.postRepository.findById(id);
        if (!post.isPresent()){
            return ResponseEntity.notFound().build();
        }else if (!post.get().getAuthor().equals(user)){
            return ResponseEntity.badRequest()
                    .body(new FormErrorDto("user-invalid", "You cannot delete a poster that is not yours"));
        }

        this.postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
