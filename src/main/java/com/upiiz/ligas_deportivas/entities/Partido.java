package com.upiiz.ligas_deportivas.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Schema(
        name = "Partido",
        description = "Representa un partido entre dos equipos dentro de una liga"
)
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único del partido",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Fecha en la que se juega el partido",
            example = "2025-04-10"
    )
    private LocalDate fecha;

    @Schema(
            description = "Cantidad de goles anotados por el equipo local",
            example = "3"
    )
    private Integer golesLocal;

    @Schema(
            description = "Cantidad de goles anotados por el equipo visitante",
            example = "1"
    )
    private Integer golesVisitante;

    @ManyToOne
    @JoinColumn(name = "equipo_local_id")
    @Schema(
            description = "Equipo que juega como local"
    )
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id")
    @Schema(
            description = "Equipo que juega como visitante"
    )
    private Equipo equipoVisitante;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    @Schema(
            description = "Liga a la que pertenece el partido"
    )
    private Liga liga;

    public Partido(){ }

    public Partido(LocalDate fecha, Integer golesLocal, Integer golesVisitante) {
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    // ---------------- GETTERS Y SETTERS ----------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getGolesLocal() { return golesLocal; }
    public void setGolesLocal(Integer golesLocal) { this.golesLocal = golesLocal; }

    public Integer getGolesVisitante() { return golesVisitante; }
    public void setGolesVisitante(Integer golesVisitante) { this.golesVisitante = golesVisitante; }

    public Equipo getEquipoLocal() { return equipoLocal; }
    public void setEquipoLocal(Equipo equipoLocal) { this.equipoLocal = equipoLocal; }

    public Equipo getEquipoVisitante() { return equipoVisitante; }
    public void setEquipoVisitante(Equipo equipoVisitante) { this.equipoVisitante = equipoVisitante; }

    public Liga getLiga() { return liga; }
    public void setLiga(Liga liga) { this.liga = liga; }
}
