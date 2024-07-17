package com.forhub.api.dto.course;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateCourseDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotBlank(message = "La descripción es obligatoria")
        String description,

        @NotNull(message = "La fecha de inicio es obligatoria")
        @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
        LocalDate startDate,

        @NotNull(message = "La fecha de finalización es obligatoria")
        LocalDate endDate,

        @NotNull(message = "El ID del instructor es obligatorio")
        Long instructorId
) {
}