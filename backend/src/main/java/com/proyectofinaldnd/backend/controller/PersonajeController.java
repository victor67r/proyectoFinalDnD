package com.proyectofinaldnd.backend.controller;
import com.proyectofinaldnd.backend.entity.Personaje;
import com.proyectofinaldnd.backend.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {
    @Autowired
    private PersonajeRepository personajeRepository;

    // Este método busca todos los personajes que pertenecen a una misma sala
    @GetMapping("/sala/{idPartida}")
    public List<Personaje> obtenerPorSala(@PathVariable Long idPartida) {
        return personajeRepository.findByPartidaIdPartida(idPartida);
    }

    @PostMapping("/crear")
    public ResponseEntity<Personaje> crearPersonaje(@RequestBody Personaje nuevoPersonaje) {
        Personaje personajeGuardado = personajeRepository.save(nuevoPersonaje);
        return ResponseEntity.ok(personajeGuardado);
    }
}
