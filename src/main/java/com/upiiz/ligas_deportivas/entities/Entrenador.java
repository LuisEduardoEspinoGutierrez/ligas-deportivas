package com.upiiz.ligas_deportivas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad JPA que representa a un entrenador dentro del sistema.
 */
@Entity
@Table(name = "entrenadores")
@Schema(description = "Entidad que representa a un entrenador de un equipo")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único del entrenador",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Schema(
            description = "Nombre completo del entrenador",
            example = "Enrique Martín",
            required = true
    )
    private String nombre;

    @NotNull
    @Schema(
            description = "Años de experiencia del entrenador",
            example = "10",
            required = true
    )
    private int experiencia;

    @NotBlank
    @Size(max = 100)
    @Schema(
            description = "Nacionalidad del entrenador",
            example = "España",
            required = true
    )
    private String nacionalidad;

    @OneToOne(mappedBy = "entrenador")
    @JsonIgnoreProperties("entrenador")
    @Schema(
            description = "Equipo al que está asignado este entrenador (si aplica)",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Equipo equipo;

    public Entrenador() {}

    public Entrenador(String nombre, int experiencia, String nacionalidad) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.nacionalidad = nacionalidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
}



