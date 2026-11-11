package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.entity.Personaje;
import com.proyectofinaldnd.backend.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personajes")
@CrossOrigin(origins = "*") // Permite a Luis conectarse desde React
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepository;

    // Para que Luis cargue el Cuadrante 1 de su pantalla
    @GetMapping("/{id}")
    public Personaje obtenerPersonaje(@PathVariable Long id) {
        return personajeRepository.findById(id).orElse(null);
    }

    // Crear personajes de prueba desde Thunder Client
    @PostMapping("/crear")
    public Personaje crearPersonaje(@RequestBody Personaje personaje) {
        return personajeRepository.save(personaje);
    }
}