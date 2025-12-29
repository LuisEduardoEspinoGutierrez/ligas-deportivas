package com.upiiz.ligas_deportivas.repositories;

import com.upiiz.ligas_deportivas.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    List<Partido> findByLigaId(Long ligaId);
}

