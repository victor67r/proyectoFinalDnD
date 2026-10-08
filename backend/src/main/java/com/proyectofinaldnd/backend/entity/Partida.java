package com.proyectofinaldnd.backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartida;

    @Column(nullable = false, unique = true, length = 20)
    private String codigoSala;

    // Relación de clave foránea con la tabla Usuario (El DM)
    @ManyToOne
    @JoinColumn(name = "id_dm", nullable = false)
    private Usuario dm;

    public Partida() {}

    public Long getIdPartida() { return idPartida; }
    public void setIdPartida(Long idPartida) { this.idPartida = idPartida; }
    public String getCodigoSala() { return codigoSala; }
    public void setCodigoSala(String codigoSala) { this.codigoSala = codigoSala; }
    public Usuario getDm() { return dm; }
    public void setDm(Usuario dm) { this.dm = dm; }
}
