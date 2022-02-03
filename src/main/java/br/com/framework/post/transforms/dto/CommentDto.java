package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Comment;

import java.time.LocalDateTime;

public class CommentDto {

    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private String nomeAuthor;


    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.nomeAuthor = comment.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getNomeAuthor() {
        return nomeAuthor;
    }
}
