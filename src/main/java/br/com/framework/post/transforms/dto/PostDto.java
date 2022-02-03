package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class PostDto {

    private Long id;
    private String text;
    private String image;
    private String link;
    private LocalDateTime createdAt;

    public PostDto(Post post) {
        this.id = post.getId();
        this.text = post.getText();
        this.image = post.getImage();
        this.link = post.getLink();
        this.createdAt = post.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static Page<PostDto> converter(Page<Post> posts){
        return posts.map(PostDto::new);
    }
}
