package br.com.framework.post.security.services;

import br.com.framework.post.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        User logado = (User) authentication.getPrincipal();
        Calendar now = Calendar.getInstance();
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTimeInMillis(now.getTimeInMillis() + Long.parseLong(this.expiration));

        return Jwts.builder()
                .setIssuer("Api Teste Framework")
                .setSubject(logado.getId().toString())
                .setIssuedAt(now.getTime())
                .setExpiration(expirationDate.getTime())
                .signWith(SignatureAlgorithm.HS256,  this.secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Long getIdUSer(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
