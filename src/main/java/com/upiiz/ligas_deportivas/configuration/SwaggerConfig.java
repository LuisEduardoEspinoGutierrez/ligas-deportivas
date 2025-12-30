package com.upiiz.ligas_deportivas.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Desarrollo de una API para Gestión de Ligas Deportivas",
                version = "1.0",
                description = "Documentación de la API REST para la gestión de Ligas. \\nRepositorio: https://github.com/LuisEduardoEspinoGutierrez",
                termsOfService = "https://www.zacatecas.ipn.mx",
                contact = @Contact(
                        name = "Soporte API",
                        email = "espinogutierrezluiseduardo@gmail.com",
                        url = "https://mail.google.com"
                ),
                license = @License(
                        name = "Licencia MIT",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        /*
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Servidor local de desarrollo"
                )


        },
        */


        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfig {}

