package com.forhub.api.service;

import com.forhub.api.dto.course.CourseDTO;
import com.forhub.api.dto.course.CreateCourseDTO;
import com.forhub.api.dto.course.UpdateCourseDTO;
import com.forhub.api.infra.errores.DuplicateEntityException;
import com.forhub.api.model.Course;
import com.forhub.api.model.User;
import com.forhub.api.repository.CourseRepository;
import com.forhub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public CourseDTO createCourse(CreateCourseDTO createCourseDTO) {
        if (courseRepository.existsByName(createCourseDTO.name())) {
            throw new DuplicateEntityException("Course with the same name already exists");
        }

        User instructor = userRepository.findById(createCourseDTO.instructorId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));

        Course course = new Course();
        course.setName(createCourseDTO.name());
        course.setDescription(createCourseDTO.description());
        course.setStartDate(createCourseDTO.startDate());
        course.setEndDate(createCourseDTO.endDate());
        course.setInstructor(instructor);
        courseRepository.save(course);
        return mapToDTO(course);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CourseDTO> getActiveCourses(LocalDate currentDate) {
        return courseRepository.findByStartDateBeforeAndEndDateAfter(currentDate, currentDate)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CourseDTO> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getCoursesByKeyword(String keyword) {
        return courseRepository.findByNameContainingOrDescriptionContaining(keyword, keyword)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        return mapToDTO(course);
    }

    public CourseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        User instructor = userRepository.findById(updateCourseDTO.instructorId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));

        course.setName(updateCourseDTO.name());
        course.setDescription(updateCourseDTO.description());
        course.setStartDate(updateCourseDTO.startDate());
        course.setEndDate(updateCourseDTO.endDate());
        course.setInstructor(instructor);
        courseRepository.save(course);
        return mapToDTO(course);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        courseRepository.delete(course);
    }

    private CourseDTO mapToDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getStartDate(),
                course.getEndDate(),
                course.getInstructor().getId()
        );
    }
}
