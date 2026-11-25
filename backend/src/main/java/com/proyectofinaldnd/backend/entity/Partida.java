package com.proyectofinaldnd.backend.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartida;

    @Column(nullable = false, unique = true, length = 20)
    private String codigoSala;

    // Usuario que creó la partida como DM
    @ManyToOne
    @JoinColumn(name = "id_dm", nullable = false)
    private Usuario dm;

    // Jugadores que participan en la partida
    @ManyToMany
    @JoinTable(
        name = "partida_jugadores",
        joinColumns = @JoinColumn(name = "id_partida"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> jugadores = new HashSet<>();

    public Partida() {
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public Usuario getDm() {
        return dm;
    }

    public void setDm(Usuario dm) {
        this.dm = dm;
    }

    public Set<Usuario> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Usuario> jugadores) {
        this.jugadores = jugadores;
    }
}