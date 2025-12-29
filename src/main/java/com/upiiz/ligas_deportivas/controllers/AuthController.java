package com.upiiz.ligas_deportivas.controllers;

import com.upiiz.ligas_deportivas.dto.AuthRequest;
import com.upiiz.ligas_deportivas.dto.AuthResponse;
import com.upiiz.ligas_deportivas.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Autenticación",
        description = "Endpoints para registrar usuarios e iniciar sesión usando JWT"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ---------------------- REGISTRO ----------------------

    @Operation(
            summary = "Registrar un usuario nuevo",
            description = "Crea una cuenta de usuario enviando un nombre de usuario y una contraseña."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario registrado exitosamente",
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(
            responseCode = "400",
            description = "El usuario ya existe"
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthRequest.class))
            )
            @RequestBody AuthRequest req
    ) {
        String msg = authService.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(msg);
    }

    // ---------------------- LOGIN ----------------------

    @Operation(
            summary = "Iniciar sesión",
            description = "Valida las credenciales y devuelve un token JWT para futuras peticiones."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Inicio de sesión exitoso. Devuelve un JWT.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "Credenciales incorrectas"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciales del usuario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthRequest.class))
            )
            @RequestBody AuthRequest req
    ) {
        String token = authService.login(req);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

