package br.com.framework.post.transforms.form;

import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentCreateForm {
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String comment;

    @NotNull
    private Long postId;

    public String getComment() {
        return comment;
    }

    public Long getPostId() {
        return postId;
    }

    public Comment converter(Post post, User author) {
        return new Comment(this.comment, post, author);
    }
}
