package br.com.framework.post.controllers;

import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;
import br.com.framework.post.repositories.ICommentRepository;
import br.com.framework.post.repositories.IPostRepository;
import br.com.framework.post.security.services.AuthenticationService;
import br.com.framework.post.transforms.dto.CommentDto;
import br.com.framework.post.transforms.dto.FormErrorDto;
import br.com.framework.post.transforms.dto.PostDto;
import br.com.framework.post.transforms.dto.UserDto;
import br.com.framework.post.transforms.form.CommentCreateForm;
import br.com.framework.post.transforms.form.PostCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IPostRepository  postRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> created(@RequestBody @Valid CommentCreateForm createForm){
        User user = this.authenticationService.getUser();

        Optional<Post> post = this.postRepository.findById(createForm.getPostId());

        if (!post.isPresent()){
            return ResponseEntity.badRequest().body(new FormErrorDto("Post", "Post not exist"));
        }

        Comment comment = createForm.converter(post.get(), user);
        this.commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentDto(comment));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        User user = this.authenticationService.getUser();

        Optional<Comment> comment = this.commentRepository.findById(id);
        if (!comment.isPresent()){
            return ResponseEntity.notFound().build();
        }else if (!comment.get().getAuthor().equals(user)){
            return ResponseEntity.badRequest()
                    .body(new FormErrorDto("user-invalid", "You cannot delete a comment that is not yours"));
        }

        this.commentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{id}")
    public List<CommentDto> listByPostId(@PathVariable Long id){
        List<Comment> comments = this.commentRepository.findByPostId(id);
        return CommentDto.converter(comments);
    }
}
