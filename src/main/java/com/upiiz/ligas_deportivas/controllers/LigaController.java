package com.upiiz.ligas_deportivas.controllers;

import com.upiiz.ligas_deportivas.dto.LigaCreateRequest;
import com.upiiz.ligas_deportivas.entities.Liga;
import com.upiiz.ligas_deportivas.services.LigaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ligas")
@Tag(
        name = "Ligas",
        description = "Operaciones relacionadas con ligas: listar, crear, actualizar y eliminar ligas"
)
public class LigaController {

    private final LigaService ligaService;

    public LigaController(LigaService ligaService){
        this.ligaService = ligaService;
    }

    // ---------------------- LISTAR LIGAS ----------------------

    @Operation(
            summary = "Listar ligas",
            description = "Obtiene la lista completa de ligas registradas."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de ligas obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Liga.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Liga>> listar() {
        return ResponseEntity.ok(ligaService.getAll());
    }

    // ---------------------- OBTENER LIGA POR ID ----------------------

    @Operation(
            summary = "Obtener liga por ID",
            description = "Obtiene la información de una liga específica por su ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liga encontrada",
            content = @Content(schema = @Schema(implementation = Liga.class))
    )
    @GetMapping("/{id}")
    public ResponseEntity<Liga> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ligaService.getById(id));
    }

    // ---------------------- CREAR LIGA ----------------------

    @Operation(
            summary = "Crear una nueva liga",
            description = "Registra una liga nueva enviando nombre, país y temporada actual."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liga creada correctamente",
            content = @Content(schema = @Schema(implementation = Liga.class))
    )
    @PostMapping
    public ResponseEntity<Liga> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información necesaria para crear una liga",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LigaCreateRequest.class))
            )
            @RequestBody LigaCreateRequest req
    ) {
        Liga l = new Liga(
                req.getNombre(),
                req.getPais(),
                req.getTemporadaActual()
        );
        return ResponseEntity.ok(ligaService.create(l));
    }

    // ---------------------- ACTUALIZAR LIGA ----------------------

    @Operation(
            summary = "Actualizar liga",
            description = "Actualiza los datos de una liga existente."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liga actualizada correctamente",
            content = @Content(schema = @Schema(implementation = Liga.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<Liga> actualizar(
            @PathVariable Long id,
            @RequestBody LigaCreateRequest req
    ) {
        Liga liga = new Liga(
                req.getNombre(),
                req.getPais(),
                req.getTemporadaActual()
        );
        return ResponseEntity.ok(ligaService.update(id, liga));
    }

    // ---------------------- ELIMINAR LIGA ----------------------

    @Operation(
            summary = "Eliminar liga",
            description = "Elimina una liga por su ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Liga eliminada correctamente"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ligaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


