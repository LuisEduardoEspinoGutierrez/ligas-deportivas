package com.upiiz.ligas_deportivas.services;

import com.upiiz.ligas_deportivas.entities.Entrenador;
import com.upiiz.ligas_deportivas.repositories.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository){
        this.entrenadorRepository = entrenadorRepository;
    }

    // ---------------------- LISTAR ENTRENADORES ----------------------
    public List<Entrenador> getAll(){
        return entrenadorRepository.findAll();
    }

    // ---------------------- BUSCAR POR ID ----------------------
    public Entrenador getById(Long id){
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
    }

    // ---------------------- CREAR ENTRENADOR ----------------------
    public Entrenador create(Entrenador e){
        return entrenadorRepository.save(e);
    }

    // ---------------------- ACTUALIZAR ENTRENADOR ----------------------
    public Entrenador update(Long id, Entrenador entrenador){

        Entrenador entrenadorExistente = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        entrenadorExistente.setNombre(entrenador.getNombre());
        entrenadorExistente.setNacionalidad(entrenador.getNacionalidad());
        entrenadorExistente.setExperiencia(entrenador.getExperiencia());

        return entrenadorRepository.save(entrenadorExistente);
    }

    // ---------------------- ELIMINAR ENTRENADOR ----------------------
    public void delete(Long id){
        entrenadorRepository.deleteById(id);
    }
}

