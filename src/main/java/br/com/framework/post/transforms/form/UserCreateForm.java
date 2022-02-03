package br.com.framework.post.transforms.form;

import br.com.framework.post.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserCreateForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User converter() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
        return new User(this.name, this.email, this.password);
    }
}
