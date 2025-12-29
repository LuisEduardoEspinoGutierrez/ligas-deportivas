package com.upiiz.ligas_deportivas.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ligas")
@Schema(description = "Entidad que representa una liga deportiva compuesta por múltiples equipos")
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único de la liga",
            example = "3",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Nombre oficial de la liga",
            example = "Liga MX",
            required = true
    )
    private String nombre;

    @Schema(
            description = "País al que pertenece la liga",
            example = "México",
            required = true
    )
    private String pais;

    @Schema(
            description = "Temporada actual que disputa la liga",
            example = "2024-2025",
            required = true
    )
    private String temporadaActual;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("liga")
    @Schema(
            description = "Lista de equipos que participan en la liga",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private List<Equipo> equipos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "liga")
    @Schema(
            description = "Lista de partidos que tiene la liga",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private List<Partido> partidos = new ArrayList<>();


    public Liga() {}

    public Liga(String nombre, String pais, String temporadaActual) {
        this.nombre = nombre;
        this.pais = pais;
        this.temporadaActual = temporadaActual;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getTemporadaActual() { return temporadaActual; }
    public void setTemporadaActual(String temporadaActual) { this.temporadaActual = temporadaActual; }

    public List<Equipo> getEquipos() { return equipos; }
    public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }
}


