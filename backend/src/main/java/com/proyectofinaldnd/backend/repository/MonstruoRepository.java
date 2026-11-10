package com.proyectofinaldnd.backend.repository;
import com.proyectofinaldnd.backend.entity.Monstruo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonstruoRepository extends JpaRepository<Monstruo, Long> {}