package com.upiiz.ligas_deportivas.controllers;

import com.upiiz.ligas_deportivas.dto.JugadorCreateRequest;
import com.upiiz.ligas_deportivas.entities.Jugador;
import com.upiiz.ligas_deportivas.services.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@Tag(
        name = "Jugadores",
        description = "Operaciones relacionadas con jugadores: listar, crear, actualizar y eliminar"
)
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService){
        this.jugadorService = jugadorService;
    }

    // ---------------------- LISTAR JUGADORES ----------------------

    @Operation(
            summary = "Listar jugadores",
            description = "Obtiene la lista completa de jugadores registrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de jugadores obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jugador.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Jugador>> listar() {
        return ResponseEntity.ok(jugadorService.getAll());
    }

    // ---------------------- OBTENER POR ID ----------------------

    @Operation(
            summary = "Obtener jugador por ID",
            description = "Obtiene la información de un jugador específico."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Jugador encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jugador.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtener(@PathVariable Long id){
        return ResponseEntity.ok(jugadorService.getById(id));
    }

    // ---------------------- CREAR JUGADOR ----------------------

    @Operation(
            summary = "Crear un nuevo jugador",
            description = "Registra un jugador nuevo enviando nombre, posición, número y el ID del equipo."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Jugador creado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jugador.class)
            )
    )
    @PostMapping
    public ResponseEntity<Jugador> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información necesaria para crear un jugador",
                    required = true,
                    content = @Content(schema = @Schema(implementation = JugadorCreateRequest.class))
            )
            @RequestBody JugadorCreateRequest req
    ) {
        Jugador j = new Jugador(
                req.getNombre(),
                req.getPosicion(),
                req.getNumero()

        );
        return ResponseEntity.ok(jugadorService.create(j, req.getEquipoId()));
    }

    // ---------------------- ACTUALIZAR JUGADOR ----------------------

    @Operation(
            summary = "Actualizar jugador",
            description = "Actualiza los datos de un jugador existente."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Jugador actualizado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jugador.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(
            @PathVariable Long id,
            @RequestBody JugadorCreateRequest req
    ){
        Jugador j = new Jugador(
                req.getNombre(),
                req.getPosicion(),
                req.getNumero()

        );
        return ResponseEntity.ok(jugadorService.update(id, j, req.getEquipoId()));
    }

    // ---------------------- ELIMINAR JUGADOR ----------------------

    @Operation(
            summary = "Eliminar jugador",
            description = "Elimina un jugador por su ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Jugador eliminado correctamente"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
