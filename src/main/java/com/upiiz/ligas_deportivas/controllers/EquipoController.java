package com.upiiz.ligas_deportivas.controllers;

import com.upiiz.ligas_deportivas.dto.EquipoCreateRequest;
import com.upiiz.ligas_deportivas.entities.Equipo;
import com.upiiz.ligas_deportivas.services.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@Tag(
        name = "Equipos",
        description = "Gestión de equipos: listar, crear, actualizar y eliminar"
)
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService){
        this.equipoService = equipoService;
    }

    // ---------------------- LISTAR ----------------------

    @Operation(
            summary = "Listar equipos",
            description = "Regresa todos los equipos registrados en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de equipos obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Equipo.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(equipoService.getAll());
    }

    // ---------------------- OBTENER POR ID ----------------------

    @Operation(
            summary = "Obtener equipo por ID",
            description = "Obtiene la información de un equipo específico."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Equipo encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Equipo.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtener(@PathVariable Long id){
        return ResponseEntity.ok(equipoService.getById(id));
    }

    // ---------------------- CREAR ----------------------

    @Operation(
            summary = "Crear un nuevo equipo",
            description = "Crea un equipo enviando nombre, ciudad y el ID de la liga."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Equipo creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Equipo.class)
            )
    )
    @PostMapping
    public ResponseEntity<Equipo> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para registrar un equipo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EquipoCreateRequest.class))
            )
            @RequestBody EquipoCreateRequest req
    ) {
        Equipo e = new Equipo(req.getNombre(), req.getCiudad());
        return ResponseEntity.ok(equipoService.create(e, req.getLigaId(), req.getEntrenadorId()));
    }

    // ---------------------- ACTUALIZAR ----------------------

    @Operation(
            summary = "Actualizar equipo",
            description = "Actualiza los datos de un equipo existente."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Equipo actualizado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Equipo.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizar(
            @PathVariable Long id,
            @RequestBody EquipoCreateRequest req
    ){
        Equipo e = new Equipo(req.getNombre(), req.getCiudad());
        return ResponseEntity.ok(equipoService.update(id, e, req.getLigaId(), req.getEntrenadorId()));
    }

    // ---------------------- ELIMINAR ----------------------

    @Operation(
            summary = "Eliminar equipo",
            description = "Elimina un equipo por su ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Equipo eliminado correctamente"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



