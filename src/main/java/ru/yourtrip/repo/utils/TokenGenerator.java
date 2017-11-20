package ru.yourtrip.repo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import ru.yourtrip.repo.models.Person;

import java.security.Key;

public class TokenGenerator {
    public static final Key key = MacProvider.generateKey();

    private TokenGenerator() { }

    public static String getToken(Person person) {
        Claims claims = Jwts.claims();
        claims.put("user", person.getLogin());
        claims.put("password", person.getHash());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}