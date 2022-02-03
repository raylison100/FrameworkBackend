package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Album;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        this.posts = album.getPosts().stream().map(PostDetailDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getNomeAuthor() {
        return nomeAuthor;
    }

    public List<PostDetailDto> getPosts() {
        return posts;
    }
}
