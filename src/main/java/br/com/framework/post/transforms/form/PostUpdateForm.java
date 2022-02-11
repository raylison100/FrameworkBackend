package br.com.framework.post.transforms.form;

import br.com.framework.post.models.Post;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class PostUpdateForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String text;

    private String link;

    public Post update(Post post) {
        post.setText(this.text);
        post.setLink(this.link);

        return post;
    }
}
