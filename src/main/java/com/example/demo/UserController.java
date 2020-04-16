package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;

@RestController
public class UserController {

    public static final int ONE_MINUTE = 60000;

    private Key getSigningKey() {
        byte[] keyBytes = ("fkwda<NJ7&M~rQJRr#6%6F{6;KWz?~").getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody User user) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("role", "USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 2 * ONE_MINUTE))
                .signWith(SignatureAlgorithm.HS256, "fkwda<NJ7&M~rQJRr#6%6F{6;KWz?~")
                .compact();
    }
}
