package br.com.framework.post.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String comment;

	@ManyToOne
	private Post post;

	@ManyToOne
	private User author;

	private LocalDateTime createdAt = LocalDateTime.now();

	public Comment() {
	}

	public Comment(String comment, Post post, User author) {
		this.comment = comment;
		this.post = post;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String mensagem) {
		this.comment = mensagem;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User autor) {
		this.author = autor;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime dataCriacao) {
		this.createdAt = dataCriacao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return Objects.equals(id, comment.id) && Objects.equals(comment, comment.comment) && Objects.equals(post, comment.post) && Objects.equals(author, comment.author) && Objects.equals(createdAt, comment.createdAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, comment, post, author, createdAt);
	}
}
