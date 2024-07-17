package com.forhub.api.controller;

import com.forhub.api.dto.course.CourseDTO;
import com.forhub.api.dto.course.CreateCourseDTO;
import com.forhub.api.dto.course.CreateCourseListDTO;
import com.forhub.api.dto.course.SearchRequest;
import com.forhub.api.dto.course.UpdateCourseDTO;
import com.forhub.api.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        CourseDTO newCourse = courseService.createCourse(createCourseDTO);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    @ResponseBody
    public ResponseEntity<List<CourseDTO>> createCourses(@Valid @RequestBody CreateCourseListDTO createCourseListDTO) {
        List<CourseDTO> newCourses = createCourseListDTO.courses().stream()
                .map(courseService::createCourse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(newCourses, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CourseDTO>> getActiveCourses() {
        List<CourseDTO> courses = courseService.getActiveCourses(LocalDate.now());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructor(@PathVariable Long instructorId) {
        List<CourseDTO> courses = courseService.getCoursesByInstructor(instructorId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CourseDTO>> getCoursesByKeyword(@RequestBody SearchRequest searchRequest) {
        List<CourseDTO> courses = courseService.getCoursesByKeyword(searchRequest.keyword());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody UpdateCourseDTO updateCourseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, updateCourseDTO);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
