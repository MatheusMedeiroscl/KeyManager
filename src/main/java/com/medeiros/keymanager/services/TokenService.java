package com.medeiros.keymanager.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.medeiros.keymanager.entities.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public static Logger log =  LoggerFactory.getLogger(TokenService.class);


    public String generateToken(UserEntity user){
        log.info("GENERATE TOKEN SERVICE");
        try {
            log.info("SECRET KEY VALIDATE:{}", secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth_api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException("ERROR TO GENERATE THE TOKEN", exception);
        }

    };

    public String validateToken(String token){
        log.info("VALIDATE TOKEN SERVICE");
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth_api")
                    .build()
                    .verify(token)
                    .getSubject(); //email

        }catch (JWTVerificationException exception){
            log.error("THE TOKEN IS NOT VALID");
            return null;
        }

    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}

