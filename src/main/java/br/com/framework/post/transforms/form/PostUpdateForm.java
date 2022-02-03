package br.com.framework.post.transforms.form;

import br.com.framework.post.models.Post;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostUpdateForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String text;

    @NotNull
    @NotEmpty
    private String image;

    private String link;

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public Post update(Post post) {
        post.setText(this.text);
        post.setImage(this.image);
        post.setLink(this.link);

        return post;
    }
}
