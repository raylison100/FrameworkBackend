package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import lombok.Getter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDetailDto {

    private Long id;
    private String text;
    private String image;
    private String link;
    private String type;
    private String nameAuthor;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;

    public PostDetailDto() {

    }

    public PostDetailDto(Long id, String text, String link, String nameAuthor, LocalDateTime createdAt, List<Comment> comments) {
        this.id = id;
        this.text = text;
        this.link = link;
        this.nameAuthor = nameAuthor;
        this.createdAt = createdAt;
        this.comments = comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }

    private void setImageInfo(Post post){
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(String.valueOf(post.getId()))
                .toUriString();

        this.image = fileDownloadUri;
        this.type = post.getType();
    }

    public static PostDetailDto converter(Post post){
        return new PostDetailDto(
                post.getId(),
                post.getText(),
                post.getLink(),
                post.getAuthor().getName(),
                post.getCreatedAt(),
                post.getComments()
        );
    }
}
