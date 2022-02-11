package br.com.framework.post.repositories;

import br.com.framework.post.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    Comment findByAuthorId(Long idAuthor);

    List<Comment> findByPostId(Long idPost);
}
