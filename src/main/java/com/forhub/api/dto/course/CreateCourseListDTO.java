package com.forhub.api.dto.course;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateCourseListDTO(
        @NotEmpty(message = "La lista de cursos no puede estar vacía")
        @Valid List<CreateCourseDTO> courses
) {
}
