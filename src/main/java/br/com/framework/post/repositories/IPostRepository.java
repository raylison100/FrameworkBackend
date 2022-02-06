package br.com.framework.post.repositories;

import br.com.framework.post.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthorId(Long idAuthor);

    List<Post> findByOrderByIdDesc();
}
