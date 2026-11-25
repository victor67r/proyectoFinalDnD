package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.entity.Usuario;
import com.proyectofinaldnd.backend.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Inyectamos la "trituradora" de contraseñas que definiste en SecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private com.proyectofinaldnd.backend.config.JwtUtil jwtUtil;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        
        // 1. Verificamos que el correo no esté ya registrado para evitar errores en MySQL
        if (usuarioRepository.findByEmail(nuevoUsuario.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: El email ya está registrado.");
        }

        // 2. Encriptamos la contraseña plana (ej. "1234") a un hash seguro (ej. "$2a$10$wY...")
        String hashPassword = passwordEncoder.encode(nuevoUsuario.getPassword());
        nuevoUsuario.setPassword(hashPassword);

        // 3. Guardamos el usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok("Usuario registrado exitosamente.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario credenciales) {
        
        // 1. Buscamos al usuario en la base de datos por su email
        Usuario usuarioBD = usuarioRepository.findByEmail(credenciales.getEmail());

        // 2. Comprobamos si existe y si la contraseña coincide con la encriptada
        // El método matches() tritura la que entra y la compara con el hash de MySQL
        if (usuarioBD == null || !passwordEncoder.matches(credenciales.getPassword(), usuarioBD.getPassword())) {
            return ResponseEntity.status(401).body("Error: Credenciales incorrectas.");
        }

        // 3. Si todo es correcto, generamos el Token JWT (el pase VIP)
        String token = jwtUtil.generarToken(usuarioBD.getEmail());

        // 4. Se lo devolvemos al frontend en un JSON limpio
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("token", token);
        respuesta.put("nombre", usuarioBD.getNombre()); // Le mandamos también el nombre para que Luis pueda poner "Hola, Marcelo" en la pantalla
        
        return ResponseEntity.ok(respuesta);
    }
}
