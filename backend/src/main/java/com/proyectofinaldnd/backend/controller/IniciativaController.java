package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.dto.ParticipanteDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/iniciativa")
public class IniciativaController {

    @PostMapping("/ordenar")
    public List<ParticipanteDTO> ordenarIniciativa(
            @RequestBody List<ParticipanteDTO> participantes) {

        return participantes.stream()
                .sorted(Comparator.comparingInt(
                    ParticipanteDTO::tirada
                ).reversed())
                .toList();
    }
}