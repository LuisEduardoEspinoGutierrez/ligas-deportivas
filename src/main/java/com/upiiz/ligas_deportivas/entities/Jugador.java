package com.upiiz.ligas_deportivas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "jugadores")
@Schema(description = "Entidad que representa a un jugador dentro de un equipo")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único del jugador",
            example = "15",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Nombre completo del jugador",
            example = "Carlos Vela",
            required = true
    )
    private String nombre;

    @Schema(
            description = "Posición en la que juega el jugador",
            example = "Delantero",
            required = true
    )
    private String posicion;

    @Schema(
            description = "Número oficial del jugador dentro del equipo",
            example = "10",
            required = true
    )
    private int numero;

    @ManyToOne
    @JsonIgnoreProperties("jugadores")
    @JoinColumn(name = "equipo_id")
    @Schema(
            description = "Equipo al que pertenece el jugador",
            required = false
    )
    private Equipo equipo;

    public Jugador() {}

    public Jugador(String nombre, String posicion, int numero) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.numero = numero;

    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
}

