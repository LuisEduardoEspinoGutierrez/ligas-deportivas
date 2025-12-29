package com.upiiz.ligas_deportivas.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Schema(description = "Entidad que representa a un usuario del sistema, utilizado para autenticación y autorización.")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único del usuario",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(
            description = "Nombre de usuario único para iniciar sesión",
            example = "luis",
            required = true
    )
    private String username;

    @Column(nullable = false)
    @Schema(
            description = "Contraseña cifrada del usuario",
            example = "4321", // ejemplo con hash
            required = true
    )
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "role")
    @Schema(
            description = "Lista de roles asociados al usuario",
            example = "[\"ROLE_ADMIN\", \"ROLE_USER\"]"
    )
    private List<String> roles = new ArrayList<>();

    public Usuario() {}

    public Usuario(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}

