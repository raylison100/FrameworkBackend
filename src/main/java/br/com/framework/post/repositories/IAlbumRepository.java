package br.com.framework.post.repositories;

import br.com.framework.post.models.Album;
import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlbumRepository extends JpaRepository<Album, Long> {

    Album findByAuthorId(Long idAuthor);

    List<Album> findByOrderByIdDesc();
}
