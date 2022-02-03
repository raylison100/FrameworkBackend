package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Album;
import br.com.framework.post.models.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class AlbumDto {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String nomeAuthor;

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.nomeAuthor = album.getAuthor().getName();
        this.createdAt = album.getCreatedAt();
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

    public static Page<AlbumDto> converter(Page<Album> albums){
        return albums.map(AlbumDto::new);
    }
}
