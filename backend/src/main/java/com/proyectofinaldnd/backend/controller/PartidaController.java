package com.proyectofinaldnd.backend.controller;
import com.proyectofinaldnd.backend.entity.Partida;
import com.proyectofinaldnd.backend.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {
    @Autowired
    private PartidaRepository partidaRepository;

    @GetMapping
    public List<Partida> obtenerTodas() {
        return partidaRepository.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Partida> crearPartida(@RequestBody Partida nuevaPartida) {
        Partida partidaGuardada = partidaRepository.save(nuevaPartida);
        return ResponseEntity.ok(partidaGuardada);
    }
}
