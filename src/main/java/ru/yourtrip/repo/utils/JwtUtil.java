package ru.yourtrip.repo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import ru.yourtrip.repo.models.Person;

import java.sql.Date;

public class JwtUtil {

    @Value("${jwt.secret}")
    private static String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public static Person parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            Person u = new Person();
            u.setLogin(body.getSubject());
            u.setId(Long.parseLong((String) body.get("id")));
            u.setRole((String) body.get("role"));
            u.setHash((String) body.get("hash"));
            u.setAvatar((String) body.get("avatar"));
            u.setBirthday(Date.valueOf((String) body.get("birthday")));
            u.setMail((String) body.get("mail"));
            u.setNickname((String) body.get("nickname"));
            u.setHidden_nickname(Boolean.getBoolean((String) body.get("hidden_nickname")));
            u.setHidden_mail(Boolean.getBoolean((String) body.get("hidden_mail")));
            u.setHidden_birthday(Boolean.getBoolean((String) body.get("hidden_birthday")));


            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(Person u) {
        Claims claims = Jwts.claims().setSubject(u.getLogin());
        claims.put("id", u.getId() + "");
        claims.put("hash", u.getHash());
        claims.put("avatar", u.getAvatar());
        claims.put("birthday", u.getBirthday() + "");
        claims.put("mail", u.getMail());
        claims.put("nickname", u.getNickname());
        claims.put("role", u.getRole());
        claims.put("hidden_mail", u.getHidden_mail() + "");
        claims.put("hidden_nickname", u.getHidden_nickname() + "");
        claims.put("hidden_birthday", u.getHidden_birthday() + "");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}