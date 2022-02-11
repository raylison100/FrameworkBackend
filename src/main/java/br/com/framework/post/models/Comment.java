package br.com.framework.post.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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
}
