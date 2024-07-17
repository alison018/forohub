package com.forhub.api.repository;

import com.forhub.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);

    // Encontrar cursos activos
    List<Course> findByStartDateBeforeAndEndDateAfter(LocalDate currentDate1, LocalDate currentDate2);

    // Encontrar cursos por instructor
    List<Course> findByInstructorId(Long instructorId);

    // Encontrar cursos por palabra clave en nombre o descripción
    List<Course> findByNameContainingOrDescriptionContaining(String nameKeyword, String descriptionKeyword);
}