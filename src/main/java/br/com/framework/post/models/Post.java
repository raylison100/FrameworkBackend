package br.com.framework.post.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	private String image;

	private String link;

	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne
	private User author;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	public Post() {
	}

	public Post(String text, String image, String link, User author) {
		this.text = text;
		this.image = image;
		this.link = link;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime dataCriacao) {
		this.createdAt = dataCriacao;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User autor) {
		this.author = autor;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Post post = (Post) o;
		return Objects.equals(id, post.id) && Objects.equals(text, post.text) && Objects.equals(image, post.image) && Objects.equals(link, post.link) && Objects.equals(createdAt, post.createdAt) && Objects.equals(author, post.author) && Objects.equals(comments, post.comments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, text, image, link, createdAt, author, comments);
	}
}
