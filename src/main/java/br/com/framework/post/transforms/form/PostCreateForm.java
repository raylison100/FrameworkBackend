package br.com.framework.post.transforms.form;

import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostCreateForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String text;

    private String link;

    public PostCreateForm() {
    }

    public Post converter(User author) {
        return new Post(this.text, this.link, author);
    }
}
