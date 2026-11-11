package com.proyectofinaldnd.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "monstruos")
public class Monstruo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer hpActual;

    @Column(nullable = false)
    private Integer hpMax;

    @Column(nullable = false)
    private Integer armadura;

    // Constructor vacío exigido por JPA
    public Monstruo() {}

    // --- Getters y Setters ---

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
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
