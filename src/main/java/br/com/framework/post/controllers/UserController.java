package br.com.framework.post.controllers;

import br.com.framework.post.models.User;
import br.com.framework.post.repositories.IUserRepository;
import br.com.framework.post.transforms.dto.FormErrorDto;
import br.com.framework.post.transforms.dto.UserDto;
import br.com.framework.post.transforms.form.UserCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> created(@RequestBody @Valid UserCreateForm form) {

        Optional<User> optionalUser = this.userRepository.findByEmail(form.getEmail());

        if (optionalUser.isPresent()){
            return ResponseEntity.badRequest().body(new FormErrorDto("email", "Email already resgistered !"));
        }

        User user = form.converter();
        this.userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
    }
}
