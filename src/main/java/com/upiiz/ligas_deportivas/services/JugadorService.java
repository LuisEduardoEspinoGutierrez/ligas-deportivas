package com.upiiz.ligas_deportivas.services;

import com.upiiz.ligas_deportivas.entities.Equipo;
import com.upiiz.ligas_deportivas.entities.Jugador;
import com.upiiz.ligas_deportivas.repositories.EquipoRepository;
import com.upiiz.ligas_deportivas.repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;

    public JugadorService(JugadorRepository jugadorRepository,
                          EquipoRepository equipoRepository){
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
    }

    // ---------------------- LISTAR JUGADORES ----------------------
    public List<Jugador> getAll(){
        return jugadorRepository.findAll();
    }

    // ---------------------- BUSCAR POR ID ----------------------
    public Jugador getById(Long id){
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    // ---------------------- CREAR JUGADOR ----------------------
    public Jugador create(Jugador j, Long equipoId){

        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        j.setEquipo(equipo);
        equipo.getJugadores().add(j);

        return jugadorRepository.save(j);
    }

    // ---------------------- ACTUALIZAR JUGADOR ----------------------
    public Jugador update(Long id, Jugador jugador, Long equipoId){

        Jugador jugadorExistente = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        jugadorExistente.setNombre(jugador.getNombre());
        jugadorExistente.setPosicion(jugador.getPosicion());
        jugadorExistente.setNumero(jugador.getNumero());

        if (equipoId != null) {
            Equipo equipo = equipoRepository.findById(equipoId)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            jugadorExistente.setEquipo(equipo);
        }

        return jugadorRepository.save(jugadorExistente);
    }

    // ---------------------- ELIMINAR JUGADOR ----------------------
    public void delete(Long id){
        jugadorRepository.deleteById(id);
    }
}


