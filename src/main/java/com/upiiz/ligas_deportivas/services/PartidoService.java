package com.upiiz.ligas_deportivas.services;

import com.upiiz.ligas_deportivas.entities.Partido;
import com.upiiz.ligas_deportivas.entities.Equipo;
import com.upiiz.ligas_deportivas.entities.Liga;
import com.upiiz.ligas_deportivas.repositories.PartidoRepository;
import com.upiiz.ligas_deportivas.repositories.EquipoRepository;
import com.upiiz.ligas_deportivas.repositories.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;
    private final LigaRepository ligaRepository;

    public PartidoService(
            PartidoRepository partidoRepository,
            EquipoRepository equipoRepository,
            LigaRepository ligaRepository
    ) {
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
        this.ligaRepository = ligaRepository;
    }

    // ---------------- LISTAR ----------------

    public List<Partido> getAll() {
        return partidoRepository.findAll();
    }

    // ---------------- OBTENER POR ID ----------------

    public Partido getById(Long id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
    }

    // ---------------- CREAR ----------------

    public Partido create(
            Partido partido,
            Long equipoLocalId,
            Long equipoVisitanteId,
            Long ligaId
    ) {
        if (equipoLocalId.equals(equipoVisitanteId)) {
            throw new RuntimeException("El equipo local y visitante no pueden ser el mismo");
        }

        Equipo local = equipoRepository.findById(equipoLocalId)
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));

        Equipo visitante = equipoRepository.findById(equipoVisitanteId)
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));

        Liga liga = ligaRepository.findById(ligaId)
                .orElseThrow(() -> new RuntimeException("Liga no encontrada"));

        partido.setEquipoLocal(local);
        partido.setEquipoVisitante(visitante);
        partido.setLiga(liga);

        return partidoRepository.save(partido);
    }

    // ---------------- ACTUALIZAR ----------------

    public Partido update(
            Long id,
            Partido partido,
            Long equipoLocalId,
            Long equipoVisitanteId,
            Long ligaId
    ) {
        Partido existente = getById(id);

        existente.setFecha(partido.getFecha());
        existente.setGolesLocal(partido.getGolesLocal());
        existente.setGolesVisitante(partido.getGolesVisitante());

        existente.setEquipoLocal(
                equipoRepository.findById(equipoLocalId)
                        .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"))
        );

        existente.setEquipoVisitante(
                equipoRepository.findById(equipoVisitanteId)
                        .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"))
        );

        existente.setLiga(
                ligaRepository.findById(ligaId)
                        .orElseThrow(() -> new RuntimeException("Liga no encontrada"))
        );

        return partidoRepository.save(existente);
    }

    // ---------------- ELIMINAR ----------------

    public void delete(Long id) {
        partidoRepository.deleteById(id);
    }
}
