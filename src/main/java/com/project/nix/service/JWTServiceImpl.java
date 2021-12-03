package com.project.nix.service;

import com.project.nix.model.entities.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    @Value("${spring.security.jwt.key}")
    private String tokenFromProperties;

    private Key key;

    @PostConstruct
    private void doOnInit() {
        key = Keys.hmacShaKeyFor(tokenFromProperties.getBytes());
    }

    @Override
    public String generate(UserDetails userDetails) {
        return Jwts.builder().setClaims(Map.of(
                        "email", userDetails.getUsername(),
                        "password", userDetails.getPassword(),
                        "auth", userDetails.getAuthorities()
                ))
                .signWith(key)
                .compact();
    }

    @Override
    public Optional<Authentication> parse(@NonNull String token) {
        try {
            Claims claims = (Claims) Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parse(token).getBody();


            return Optional.of(new UsernamePasswordAuthenticationToken(
                    claims.get("email"),
                    claims.get("password"),
                    (List<Authority>) claims.get("auth")
            ));
        } catch (Throwable exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }
}
