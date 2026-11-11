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
}