package br.com.framework.post.controllers;

import br.com.framework.post.security.services.TokenService;
import br.com.framework.post.transforms.dto.TokenDto;
import br.com.framework.post.transforms.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginForm.converter();
        try{
            Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return ResponseEntity.ok(new TokenDto(this.tokenService.gerarToken(authentication), "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
