package com.upiiz.ligas_deportivas.repositories;


import com.upiiz.ligas_deportivas.entities.Liga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigaRepository extends JpaRepository<Liga, Long>
{

}
