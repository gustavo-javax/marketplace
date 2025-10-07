package com.Marketplace.Marketplace.util;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        // Gera uma chave segura para HS256
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());

        System.out.println("=== NOVA CHAVE SECRETA JWT ===");
        System.out.println("jwt.secret=" + secretString);
        System.out.println("Tamanho: " + secretString.length() + " caracteres");
        System.out.println("=== COPIE ESTA LINHA PARA application.properties ===");
    }
}