package com.unitech.classapi.infrastructure.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unitech.classapi.application.usecase.CreatePendingUser;
import com.unitech.classapi.domain.entity.DecodedToken;
import com.unitech.classapi.domain.entity.Token;
import com.unitech.classapi.domain.entity.User;
import com.unitech.classapi.domain.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${JWT.SECRET}")
    private String secret;

    private final Logger logger = LoggerFactory.getLogger(CreatePendingUser.class);

    public Token generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var token = JWT.create()
                    .withIssuer("class-api")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId().toString())
                    .withClaim("nome", user.getName())
                    .withClaim("email", user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return Token.builder().value(token).build();
        }
        catch(JWTCreationException e){
            throw new RuntimeException("Error while generation token", e);
        }
    }

    public DecodedToken decodeToken(String token) {
        try{
            DecodedJWT jwt = verifyToken(token);
            return extractDecodedToken(jwt);
        } catch (JWTVerificationException e) {
            logger.error("Erro ao verificar o token JWT: " + e.getMessage());

            throw new RuntimeException("Failed to decode token", e);
        }
    }

    private DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).build().verify(token);
        } catch (JWTDecodeException exception) {

            throw new RuntimeException("Failed to decode token", exception);
        }
    }

    private DecodedToken extractDecodedToken(DecodedJWT jwt) {
        try {
            UUID id = UUID.fromString(getClaimValue(jwt, "id"));
            String name = getClaimValue(jwt, "nome");
            String email = getClaimValue(jwt, "email");
            Role role = Role.valueOf(getClaimValue(jwt, "role"));

            return DecodedToken.builder().id(id).name(name).email(email).role(role).build();
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("Failed to extract decoded token", exception);
        }
    }

    private String getClaimValue(DecodedJWT jwt, String claimName) {
        return jwt.getClaim(claimName).asString();
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
