package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Album;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AlbumDetailDto {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String nomeAuthor;
    private List<PostDetailDto> posts;

    public AlbumDetailDto(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.nomeAuthor = album.getAuthor().getName();
        this.createdAt = album.getCreatedAt();
        this.posts = album.getPosts().stream().map(PostDetailDto::converter).collect(Collectors.toList());
    }
}
