package com.upiiz.ligas_deportivas.services;

import com.upiiz.ligas_deportivas.entities.Equipo;
import com.upiiz.ligas_deportivas.entities.Liga;
import com.upiiz.ligas_deportivas.entities.Entrenador;
import com.upiiz.ligas_deportivas.repositories.EntrenadorRepository;
import com.upiiz.ligas_deportivas.repositories.EquipoRepository;
import com.upiiz.ligas_deportivas.repositories.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final LigaRepository ligaRepository;
    private final EntrenadorRepository entrenadorRepository;

    public EquipoService(
            EquipoRepository equipoRepository,
            LigaRepository ligaRepository,
            EntrenadorRepository entrenadorRepository
    ) {
        this.equipoRepository = equipoRepository;
        this.ligaRepository = ligaRepository;
        this.entrenadorRepository = entrenadorRepository;
    }

    // -------- LISTAR --------
    public List<Equipo> getAll() {
        return equipoRepository.findAll();
    }

    // -------- OBTENER POR ID --------
    public Equipo getById(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    // -------- CREAR --------
    public Equipo create(Equipo equipo, Long ligaId, Long entrenadorId) {

        if (ligaId != null) {
            Liga liga = ligaRepository.findById(ligaId)
                    .orElseThrow(() -> new RuntimeException("Liga no encontrada"));
            equipo.setLiga(liga);
        }

        if (entrenadorId != null) {
            Entrenador entrenador = entrenadorRepository.findById(entrenadorId)
                    .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
            equipo.setEntrenador(entrenador);
        }

        return equipoRepository.save(equipo);
    }

    // -------- ACTUALIZAR --------
    public Equipo update(Long id, Equipo equipo, Long ligaId, Long entrenadorId) {

        Equipo equipoExistente = getById(id);

        equipoExistente.setNombre(equipo.getNombre());
        equipoExistente.setCiudad(equipo.getCiudad());

        if (ligaId != null) {
            Liga liga = ligaRepository.findById(ligaId)
                    .orElseThrow(() -> new RuntimeException("Liga no encontrada"));
            equipoExistente.setLiga(liga);
        }

        if (entrenadorId != null) {
            Entrenador entrenador = entrenadorRepository.findById(entrenadorId)
                    .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
            equipoExistente.setEntrenador(entrenador);
        }

        return equipoRepository.save(equipoExistente);
    }

    // -------- ELIMINAR --------
    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
}
