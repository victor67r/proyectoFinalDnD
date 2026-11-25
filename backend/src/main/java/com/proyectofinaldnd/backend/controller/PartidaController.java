package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.entity.Partida;
import com.proyectofinaldnd.backend.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PartidaCreadaResponse> crearPartida(
            @RequestBody CrearPartidaRequest solicitud) {

        Partida partida = partidaService.crearPartida(solicitud.idDm());

        PartidaCreadaResponse respuesta = new PartidaCreadaResponse(
                partida.getIdPartida(),
                partida.getCodigoSala(),
                partida.getDm().getNombre()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PostMapping("/unirse")
    public ResponseEntity<JugadorUnidoResponse> unirseAPartida(
            @RequestBody UnirsePartidaRequest solicitud) {

        Partida partida = partidaService.unirseAPartida(
                solicitud.codigoSala(),
                solicitud.idJugador()
        );

        JugadorUnidoResponse respuesta = new JugadorUnidoResponse(
                partida.getCodigoSala(),
                partida.getJugadores().size()
        );

        return ResponseEntity.ok(respuesta);
    }

    public record CrearPartidaRequest(Long idDm) {
    }

    public record PartidaCreadaResponse(
            Long idPartida,
            String codigoSala,
            String nombreDm
    ) {
    }

    public record UnirsePartidaRequest(
            String codigoSala,
            Long idJugador
    ) {
    }

    public record JugadorUnidoResponse(
            String codigoSala,
            int totalJugadores
    ) {
    }
}