package com.forhub.api.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SearchTopicsRequest(
        @NotNull(message = "El ID del curso es obligatorio")
        Long courseId,

        @NotBlank(message = "El año es obligatorio")
        String year
) {}