package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.dto.ParticipanteDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IniciativaControllerTest {

    private final IniciativaController controller = new IniciativaController();

    @Test
    void ordenaDeMayorATiradaAMenor() {
        List<ParticipanteDTO> participantes = List.of(
                new ParticipanteDTO("Mago", 12),
                new ParticipanteDTO("Guerrero", 18),
                new ParticipanteDTO("Goblin", 7)
        );

        List<ParticipanteDTO> resultado =
                controller.ordenarIniciativa(participantes);

        assertEquals(
                List.of("Guerrero", "Mago", "Goblin"),
                resultado.stream().map(ParticipanteDTO::nombre).toList()
        );
    }

    @Test
    void conservaElOrdenOriginalCuandoHayEmpate() {
        List<ParticipanteDTO> participantes = List.of(
                new ParticipanteDTO("Mago", 15),
                new ParticipanteDTO("Clérigo", 15),
                new ParticipanteDTO("Guerrero", 10)
        );

        List<ParticipanteDTO> resultado =
                controller.ordenarIniciativa(participantes);

        assertEquals(
                List.of("Mago", "Clérigo", "Guerrero"),
                resultado.stream().map(ParticipanteDTO::nombre).toList()
        );
    }

    @Test
    void aceptaUnaListaVacia() {
        List<ParticipanteDTO> resultado =
                controller.ordenarIniciativa(List.of());

        assertTrue(resultado.isEmpty());
    }
}