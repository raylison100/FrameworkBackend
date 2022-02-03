package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostDetailDto {

    private Long id;
    private String text;
    private String image;
    private String link;
    private String nameAuthor;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;

    public PostDetailDto(Post post) {
        this.id = post.getId();
        this.text = post.getText();
        this.image = post.getImage();
        this.link = post.getLink();
        this.createdAt = post.getCreatedAt();
        this.nameAuthor = post.getAuthor().getName();
        this.comments = post.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<CommentDto> getComments() {
        return comments;
    }
}
