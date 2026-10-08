package com.proyectofinaldnd.backend.repository;
import com.proyectofinaldnd.backend.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    Partida findByCodigoSala(String codigoSala);
}
