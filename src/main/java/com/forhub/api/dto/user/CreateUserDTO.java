package com.forhub.api.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserDTO(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String username,
        @NotBlank(message = "La contraseña es obligatoria")
        @Pattern(regexp = ".{6,}", message = "La contraseña debe tener al menos 6 caracteres")
        String password

) {
}
