package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private String nomeAuthor;


    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.nomeAuthor = comment.getAuthor().getName();
    }

    public static List<CommentDto> converter(List<Comment> comments){
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
