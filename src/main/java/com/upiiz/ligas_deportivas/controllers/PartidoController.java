package com.upiiz.ligas_deportivas.controllers;

import com.upiiz.ligas_deportivas.dto.PartidoCreateRequest;
import com.upiiz.ligas_deportivas.entities.Partido;
import com.upiiz.ligas_deportivas.services.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@Tag(
        name = "Partidos",
        description = "Operaciones relacionadas con la gestión de partidos: crear, listar, consultar, actualizar y eliminar"
)
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    // ---------------------- LISTAR PARTIDOS ----------------------

    @Operation(
            summary = "Listar partidos",
            description = "Obtiene el listado completo de partidos registrados en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de partidos obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Partido.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Partido>> listar() {
        return ResponseEntity.ok(partidoService.getAll());
    }

    // ---------------------- OBTENER PARTIDO POR ID ----------------------

    @Operation(
            summary = "Obtener partido por ID",
            description = "Obtiene la información detallada de un partido específico a partir de su identificador."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Partido encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Partido.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Partido no encontrado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Partido> obtener(
            @Schema(description = "ID del partido", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(partidoService.getById(id));
    }

    // ---------------------- CREAR PARTIDO ----------------------

    @Operation(
            summary = "Crear un partido",
            description = "Registra un nuevo partido indicando la fecha, goles y los IDs del equipo local, equipo visitante y liga."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Partido creado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Partido.class)
            )
    )
    @PostMapping
    public ResponseEntity<Partido> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información necesaria para crear un partido",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PartidoCreateRequest.class))
            )
            @RequestBody PartidoCreateRequest req
    ) {
        Partido p = new Partido(
                req.getFecha(),
                req.getGolesLocal(),
                req.getGolesVisitante()
        );

        return ResponseEntity.ok(
                partidoService.create(
                        p,
                        req.getEquipoLocalId(),
                        req.getEquipoVisitanteId(),
                        req.getLigaId()
                )
        );
    }

    // ---------------------- ACTUALIZAR PARTIDO ----------------------

    @Operation(
            summary = "Actualizar partido",
            description = "Actualiza la información de un partido existente usando su ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Partido actualizado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Partido.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<Partido> actualizar(
            @Schema(description = "ID del partido a actualizar", example = "1")
            @PathVariable Long id,
            @RequestBody PartidoCreateRequest req
    ) {
        Partido p = new Partido(
                req.getFecha(),
                req.getGolesLocal(),
                req.getGolesVisitante()
        );

        return ResponseEntity.ok(
                partidoService.update(
                        id,
                        p,
                        req.getEquipoLocalId(),
                        req.getEquipoVisitanteId(),
                        req.getLigaId()
                )
        );
    }

    // ---------------------- ELIMINAR PARTIDO ----------------------

    @Operation(
            summary = "Eliminar partido",
            description = "Elimina un partido existente a partir de su ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Partido eliminado correctamente"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Schema(description = "ID del partido a eliminar", example = "1")
            @PathVariable Long id
    ) {
        partidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
