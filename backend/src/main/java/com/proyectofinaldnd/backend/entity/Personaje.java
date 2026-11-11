package com.proyectofinaldnd.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personajes")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String clase;

    @Column(nullable = false)
    private String raza;

    @Column(nullable = false)
    private Integer hpActual;

    @Column(nullable = false)
    private Integer hpMax;

    @Column(nullable = false)
    private Integer armadura;

    // Clave foránea al jugador propietario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Clave foránea a la partida donde se está jugando
    @ManyToOne
    @JoinColumn(name = "id_partida")
    private Partida partida;

    public Personaje() {}

    // Genera aquí los Getters y Setters para todos los campos (id, nombre, clase, raza, hpActual, hpMax, armadura)
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getHpActual() {
        return hpActual;
    }

    public void setHpActual(Integer hpActual) {
        this.hpActual = hpActual;
    }

    public Integer getHpMax() {
        return hpMax;
    }

    public void setHpMax(Integer hpMax) {
        this.hpMax = hpMax;
    }

    public Integer getArmadura() {
        return armadura;
    }

    public void setArmadura(Integer armadura) {
        this.armadura = armadura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    
}