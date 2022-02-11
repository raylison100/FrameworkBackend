package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {

    private Long id;
    private String text;
    private String image;
    private String link;
    private String type;
    private LocalDateTime createdAt;

    public PostDto(Long id, String text, String link, String image, String type, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.link = link;
        this.image = image;
        this.type = type;
        this.createdAt = createdAt;
    }

    public static Page<PostDto> converterPagination(Page<Post> posts){
        return posts.map(PostDto::genericConverter);
    }

    public static List<PostDto> converter(List<Post> posts){
        return posts.stream().map(PostDto::genericConverter).collect(Collectors.toList());
    }

    public static PostDto genericConverter(Post post){
        String fileDownloadUri = "";

        if (post.getImage() != null){
            fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(post.getId()))
                    .toUriString();
        }

        return new PostDto(
                post.getId(),
                post.getText(),
                post.getLink(),
                fileDownloadUri,
                post.getType(),
                post.getCreatedAt()
        );
    }
}
