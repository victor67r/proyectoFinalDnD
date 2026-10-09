package com.proyectofinaldnd.backend.repository;
import com.proyectofinaldnd.backend.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    List<Personaje> findByPartidaIdPartida(Long idPartida);
}
