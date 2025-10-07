package com.Marketplace.Marketplace.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        try {
            // Garante que a string seja decodificada corretamente
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            log.error("Erro ao decodificar chave JWT. Verifique se é Base64 válido.", e);
            throw new RuntimeException("Chave JWT inválida", e);
        }
    }

    public String generateToken(String email) {
        try {
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            log.error("Erro ao gerar token JWT", e);
            throw new RuntimeException("Falha ao gerar token", e);
        }
    }

    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            log.error("Erro ao extrair username do token: {}", e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token, String username) {
        try {
            final String tokenUsername = extractUsername(token);
            return (tokenUsername != null &&
                    tokenUsername.equals(username) &&
                    !isTokenExpired(token));
        } catch (Exception e) {
            log.error("Token inválido: {}", e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expirationDate.before(new Date());
        } catch (Exception e) {
            log.error("Erro ao verificar expiração do token: {}", e.getMessage());
            return true; // Considera expirado em caso de erro
        }
    }
}