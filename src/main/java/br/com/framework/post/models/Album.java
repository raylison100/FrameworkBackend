package br.com.framework.post.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	private User author;

	private LocalDateTime createdAt = LocalDateTime.now();

	@OneToMany
	private List<Post> posts;

	public Album() {

	}

	public Album(String name, User author) {
		this.name = name;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Album album = (Album) o;
		return Objects.equals(id, album.id) && Objects.equals(name, album.name) && Objects.equals(posts, album.posts) && Objects.equals(author, album.author) && Objects.equals(createdAt, album.createdAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, posts, author, createdAt);
	}
}
