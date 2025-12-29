package com.upiiz.ligas_deportivas.controllers;

import com.upiiz.ligas_deportivas.dto.EntrenadorCreateRequest;
import com.upiiz.ligas_deportivas.entities.Entrenador;
import com.upiiz.ligas_deportivas.services.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(
        name = "Entrenadores",
        description = "CRUD de entrenadores del sistema"
)
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    // ---------------------- LISTAR ----------------------

    @Operation(
            summary = "Listar entrenadores",
            description = "Devuelve una lista con todos los entrenadores registrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Entrenador.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Entrenador>> listar() {
        return ResponseEntity.ok(entrenadorService.getAll());
    }

    // ---------------------- OBTENER POR ID ----------------------

    @Operation(
            summary = "Obtener entrenador por ID",
            description = "Devuelve un entrenador específico según su ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrenador encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Entrenador.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entrenadorService.getById(id));
    }

    // ---------------------- CREAR ----------------------

    @Operation(
            summary = "Crear un nuevo entrenador",
            description = "Registra un nuevo entrenador enviando nombre, experiencia y nacionalidad."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrenador creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Entrenador.class)
            )
    )
    @PostMapping
    public ResponseEntity<Entrenador> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear un entrenador",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EntrenadorCreateRequest.class))
            )
            @RequestBody EntrenadorCreateRequest req
    ) {
        Entrenador e = new Entrenador(
                req.getNombre(),
                req.getExperiencia(),
                req.getNacionalidad()
        );
        return ResponseEntity.ok(entrenadorService.create(e));
    }

    // ---------------------- ACTUALIZAR ----------------------

    @Operation(
            summary = "Actualizar entrenador",
            description = "Actualiza la información de un entrenador existente."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrenador actualizado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Entrenador.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizar(
            @PathVariable Long id,
            @RequestBody Entrenador entrenador
    ) {
        return ResponseEntity.ok(entrenadorService.update(id, entrenador));
    }

    // ---------------------- ELIMINAR ----------------------

    @Operation(
            summary = "Eliminar entrenador",
            description = "Elimina un entrenador del sistema por su ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Entrenador eliminado correctamente"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


