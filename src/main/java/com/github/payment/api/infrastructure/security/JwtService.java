package com.github.payment.api.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    @Value("${JWT_EXPIRATION_PAYMENT}")
    private Long expiration;

    @Value("${JWT_SECRET_PAYMENT}")
    private String secret;

    public String generateToken(UserDetailsImpl user){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            Instant now = Instant.now();
            Instant expiry = now.plusMillis(expiration);

            List<String> role = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return JWT.create()
                    .withIssuer("payment-api")
                    .withSubject(user.getUsername())
                    .withClaim("role", role)
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(expiry))
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token de autenticação", exception);
        }

    }

    // Valida se a assinatura é autêntica, não expirou e pertence ao usuário
    public String validateAndExtractSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("payment-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
