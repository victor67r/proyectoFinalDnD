package com.proyectofinaldnd.backend.controller;
import com.proyectofinaldnd.backend.entity.Usuario;
import com.proyectofinaldnd.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase recibe peticiones web (REST)
@RequestMapping("/api/usuarios") // La ruta base. Todas las URLs empezarán por esto.
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository; // Conecta el controlador con la base de datos

    // 1. Obtener todos los usuarios (Método GET)
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll(); // Busca todos en MySQL y los devuelve como JSON
    }

    // 2. Registrar un nuevo usuario (Método POST)
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        // Guarda el usuario que recibe en JSON dentro de la base de datos MySQL
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        return ResponseEntity.ok(usuarioGuardado); // Devuelve un estado "200 OK" y el usuario guardado
    }
}
