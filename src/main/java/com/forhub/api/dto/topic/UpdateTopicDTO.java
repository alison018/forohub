package com.forhub.api.dto.topic;

import com.forhub.api.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        @NotBlank(message = "El título es obligatorio")
        String title,

        @NotBlank(message = "El mensaje es obligatorio")
        String message,

        @NotNull(message = "El estado es obligatorio")
        Status status,

        @NotNull(message = "El ID del autor es obligatorio")
        Long authorId,

        @NotNull(message = "El ID del curso es obligatorio")
        Long courseId
) {
}
