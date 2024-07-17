package com.forhub.api.dto.course;

import jakarta.validation.constraints.NotBlank;

public record SearchRequest(
        @NotBlank(message = "La palabra clave es obligatoria")
        String keyword
) {
}