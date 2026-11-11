package com.proyectofinaldnd.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desactivamos CSRF porque nuestra API será "Stateless" y usaremos JWT, no cookies de sesión tradicionales
            .csrf(csrf -> csrf.disable()) 
            
            // Configuramos las reglas de acceso a las rutas
            .authorizeHttpRequests(auth -> auth
                // Dejamos la puerta totalmente abierta para el login y el registro
                .requestMatchers("/api/auth/**").permitAll() 
                
                // Exigimos que cualquier otra petición (crear monstruos, mover personajes, etc.) esté autenticada
                .anyRequest().authenticated() 
            );
        
        return http.build();
    }

    // Definimos el algoritmo de encriptación estándar de la industria
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
