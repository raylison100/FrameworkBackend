package br.com.framework.post.transforms.form;

import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;
import org.hibernate.validator.constraints.Length;

import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class PostCreateForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String text;

    @NotNull
    @NotEmpty
    private String image;

    private String link;

    public String getText() { return text; }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public Post converter(User author) {
        return new Post(this.text, this.image, this.link, author);
    }
}
