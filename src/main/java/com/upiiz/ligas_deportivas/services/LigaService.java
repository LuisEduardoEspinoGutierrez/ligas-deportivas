package com.upiiz.ligas_deportivas.services;

import com.upiiz.ligas_deportivas.entities.Liga;
import com.upiiz.ligas_deportivas.repositories.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaService {

    private final LigaRepository ligaRepository;

    public LigaService(LigaRepository ligaRepository){
        this.ligaRepository = ligaRepository;
    }

    // listar ligas
    public List<Liga> getAll(){
        return ligaRepository.findAll();
    }

    // crear liga
    public Liga create(Liga liga){
        return ligaRepository.save(liga);
    }

    // buscar por id
    public Liga getById(Long id){
        return ligaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liga no encontrada"));
    }

    // actualizar liga
    public Liga update(Long id, Liga liga){

        Liga ligaExistente = ligaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liga no encontrada"));

        ligaExistente.setNombre(liga.getNombre());
        ligaExistente.setPais(liga.getPais());
        ligaExistente.setTemporadaActual(liga.getTemporadaActual());

        return ligaRepository.save(ligaExistente);
    }

    // eliminar liga
    public void delete(Long id){
        ligaRepository.deleteById(id);
    }
}


