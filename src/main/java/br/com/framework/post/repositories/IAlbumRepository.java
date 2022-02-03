package br.com.framework.post.repositories;

import br.com.framework.post.models.Album;
import br.com.framework.post.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlbumRepository extends JpaRepository<Album, Long> {

    Album findByAuthorId(Long idAuthor);
}
