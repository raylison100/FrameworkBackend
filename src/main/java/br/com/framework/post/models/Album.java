package br.com.framework.post.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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
}
