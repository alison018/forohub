package com.forhub.api.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseDTO (
    @NotNull Long id,

    @NotBlank(message = "El mensaje es obligatorio")
    String message,

    @NotNull LocalDateTime creationDate,

    @NotNull Long topicId,

    @NotNull Long authorId
) {
}
