package com.proyectofinaldnd.backend.repository;

import com.proyectofinaldnd.backend.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    // Por ahora lo dejamos completamente vacío. 
    // JpaRepository ya nos regala los métodos básicos como save(), findById(), deleteById()
}
