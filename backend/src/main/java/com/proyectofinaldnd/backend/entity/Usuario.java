package com.proyectofinaldnd.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;

    // --- NUEVAS RELACIONES FASE 2 ---

    // Un usuario puede ser DM de muchas partidas
    @OneToMany(mappedBy = "dm", cascade = CascadeType.ALL)
    private List<Partida> partidasCreadas;

    // Un usuario puede tener muchos personajes en diferentes partidas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Personaje> personajes;

    // --- FIN RELACIONES ---

    public Usuario() {}
    
    // Getters y Setters...
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public List<Partida> getPartidasCreadas() { return partidasCreadas; }
    public void setPartidasCreadas(List<Partida> partidasCreadas) { this.partidasCreadas = partidasCreadas; }
    public List<Personaje> getPersonajes() { return personajes; }
    public void setPersonajes(List<Personaje> personajes) { this.personajes = personajes; }
}
