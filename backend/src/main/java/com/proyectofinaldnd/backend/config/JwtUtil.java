package com.proyectofinaldnd.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Generamos una clave secreta súper segura para firmar los tokens. 
    // OJO: En un proyecto real esto iría en un archivo oculto (ej. application.properties), pero para tu MVP de TFG nos vale así.
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Duración del token en milisegundos (ej. 24 horas)
    private final long TIEMPO_EXPIRACION = 86400000;

    // Método para crear el pase VIP
    public String generarToken(String emailUsuario) {
        return Jwts.builder()
                .setSubject(emailUsuario) // El dueño del token
                .setIssuedAt(new Date()) // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION)) // Cuándo caduca
                .signWith(key) // Lo firmamos con nuestro sello secreto
                .compact();
    }

    // Método para extraer el dueño del token
    public String obtenerEmailDelToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Método para comprobar que nadie ha falsificado el token o que no ha caducado
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}