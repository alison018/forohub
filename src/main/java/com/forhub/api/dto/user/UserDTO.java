package com.forhub.api.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(

        @NotNull Long id,

        @NotBlank(message = "El nombre de usuario es obligatorio")
        String username
) {
}
