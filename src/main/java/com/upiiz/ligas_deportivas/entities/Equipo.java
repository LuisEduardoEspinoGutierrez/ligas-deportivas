package com.upiiz.ligas_deportivas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa a un equipo dentro de una liga deportiva.
 */
@Entity
@Table(name = "equipos")
@Schema(description = "Entidad que representa a un equipo dentro de una liga deportiva")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único del equipo",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Nombre oficial del equipo",
            example = "Tigres FC",
            required = true
    )
    private String nombre;

    @Schema(
            description = "Ciudad de origen del equipo",
            example = "Monterrey",
            required = true
    )
    private String ciudad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entrenador_id")
    @JsonIgnoreProperties("equipo")
    @Schema(
            description = "Entrenador asignado al equipo",
            required = false
    )
    private Entrenador entrenador;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    @JsonIgnoreProperties("equipos")
    @Schema(
            description = "Liga a la que pertenece el equipo",
            required = false
    )
    private Liga liga;

    @OneToMany(
            mappedBy = "equipo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("equipo")
    @Schema(
            description = "Lista de jugadores pertenecientes al equipo",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private List<Jugador> jugadores = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "equipoLocal")
    private List<Partido> partidosLocal = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "equipoVisitante")
    private List<Partido> partidosVisitante = new ArrayList<>();




    public Equipo() {}

    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    public Liga getLiga() { return liga; }
    public void setLiga(Liga liga) { this.liga = liga; }

    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }
}


