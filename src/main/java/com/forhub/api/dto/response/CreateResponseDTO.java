package com.forhub.api.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponseDTO(
        @NotBlank(message = "El mensaje es obligatorio")
        String message,

        @NotNull(message = "El ID del tópico es obligatorio")
        Long topicId,

        @NotNull(message = "El ID del autor es obligatorio")
        Long authorId
) {
}
