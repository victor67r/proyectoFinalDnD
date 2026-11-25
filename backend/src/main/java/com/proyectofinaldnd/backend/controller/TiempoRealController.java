package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.dto.ParticipanteDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class TiempoRealController {

    @MessageMapping("/dados")
    @SendTo("/topic/dados")
    public ResultadoDado lanzarDado(TiradaRequest solicitud) {
        if (solicitud == null || solicitud.caras() == null || solicitud.caras() < 2) {
            throw new IllegalArgumentException("El dado debe tener al menos 2 caras");
        }

        int resultado = ThreadLocalRandom.current().nextInt(solicitud.caras()) + 1;
        return new ResultadoDado(resultado);
    }

    @MessageMapping("/iniciativa")
    @SendTo("/topic/iniciativa")
    public List<ParticipanteDTO> ordenarIniciativa(
            List<ParticipanteDTO> participantes) {

        return participantes.stream()
                .sorted(Comparator.comparingInt(ParticipanteDTO::tirada).reversed())
                .toList();
    }

    public record TiradaRequest(Integer caras) {
    }

    public record ResultadoDado(int resultado) {
    }
}