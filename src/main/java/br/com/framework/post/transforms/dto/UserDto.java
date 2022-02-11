package br.com.framework.post.transforms.dto;

import br.com.framework.post.models.User;
import lombok.Getter;

@Getter
public class UserDto {

    private Long id;
    private String email;
    private String name;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
