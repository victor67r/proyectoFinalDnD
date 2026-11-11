package com.proyectofinaldnd.backend.service;

import com.proyectofinaldnd.backend.entity.Partida;
import com.proyectofinaldnd.backend.entity.Usuario;
import com.proyectofinaldnd.backend.repository.PartidaRepository;
import com.proyectofinaldnd.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Locale;

@Service
public class PartidaService {

    private static final int MAX_INTENTOS = 10;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final PartidaRepository partidaRepository;
    private final UsuarioRepository usuarioRepository;

    public PartidaService(
            PartidaRepository partidaRepository,
            UsuarioRepository usuarioRepository) {
        this.partidaRepository = partidaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Partida crearPartida(Long idDm) {
        Usuario dm = usuarioRepository.findById(idDm)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el usuario DM con id " + idDm));

        for (int intento = 0; intento < MAX_INTENTOS; intento++) {
            String codigo = generarCodigo();

            if (!partidaRepository.existsByCodigoSala(codigo)) {
                Partida partida = new Partida();
                partida.setCodigoSala(codigo);
                partida.setDm(dm);
                return partidaRepository.save(partida);
            }
        }

        throw new IllegalStateException(
                "No se pudo generar un código de sala único. Inténtalo de nuevo.");
    }

    @Transactional
    public Partida unirseAPartida(String codigoSala, Long idJugador) {
        if (codigoSala == null || codigoSala.isBlank()) {
            throw new IllegalArgumentException("El código de sala es obligatorio");
        }

        String codigoNormalizado = codigoSala.trim().toUpperCase(Locale.ROOT);

        Partida partida = partidaRepository.findByCodigoSala(codigoNormalizado);
        if (partida == null) {
            throw new IllegalArgumentException(
                    "No existe una sala con ese código");
        }

        Usuario jugador = usuarioRepository.findById(idJugador)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el usuario con id " + idJugador));

        if (partida.getDm().getIdUsuario().equals(idJugador)) {
            throw new IllegalArgumentException(
                    "El DM ya es el creador de esta partida");
        }

        if (!partida.getJugadores().add(jugador)) {
            throw new IllegalArgumentException(
                    "El jugador ya está en esta partida");
        }

        return partidaRepository.save(partida);
    }

    private String generarCodigo() {
        int numero = RANDOM.nextInt(9000) + 1000;
        return "DND-" + numero;
    }
}