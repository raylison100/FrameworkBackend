package br.com.framework.post.transforms.form;

import br.com.framework.post.models.Album;
import br.com.framework.post.models.Comment;
import br.com.framework.post.models.Post;
import br.com.framework.post.models.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlbumCreateForm {
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String name;

    public String getName() {
        return name;
    }

    public Album converter(User author) {
        return new Album(this.name, author);
    }
}
