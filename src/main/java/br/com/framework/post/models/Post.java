package br.com.framework.post.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	private String link;

	@Lob
	private byte[] image;

	private String type;

	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne
	private User author;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();

	public Post() {
	}

	public Post(String text, String link, User author) {
		this.text = text;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Post post = (Post) o;
		return Objects.equals(id, post.id) && Objects.equals(text, post.text) && Objects.equals(link, post.link) && Arrays.equals(image, post.image) && Objects.equals(type, post.type) && Objects.equals(createdAt, post.createdAt) && Objects.equals(author, post.author) && Objects.equals(comments, post.comments);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, text, link, type, createdAt, author, comments);
		result = 31 * result + Arrays.hashCode(image);
		return result;
	}
}
