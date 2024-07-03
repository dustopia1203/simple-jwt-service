package com.example.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "dustopia",
                        email = "nhhtony2k3@gmail.com",
                        url = "www.dustopia.th.com"
                ),
                description = "OpenApi documentation for Spring security core API",
                title = "Spring security core API",
                version = "1.0",
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/license/mit"
                ),
                termsOfService = "Term of service"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local ENV"
                ),
                @Server(
                        url = "https://auth.dustopia.com",
                        description = "Product ENV"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth for service",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {
}
