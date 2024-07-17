package com.forhub.api.repository;

import com.forhub.api.model.Status;
import com.forhub.api.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndMessage(String title, String message);

    List<Topic> findTop10ByOrderByCreationDateAsc();

    Page<Topic> findByCourseAndCreationDateBetween(Long courseId, String startDate, String endDate, Pageable pageable);


    // Encontrar temas abiertos
    List<Topic> findByStatus(Status status);

    // Encontrar temas populares (supongamos que hay un campo 'views' y 'responsesCount' en 'Topic')
    List<Topic> findTop10ByOrderByCreationDateDesc();

    // Encontrar temas por curso
    List<Topic> findByCourseId(Long courseId);

    // Encontrar temas con respuestas recientes (supongamos que hay un campo 'lastResponseDate' en 'Topic')
    List<Topic> findByCreationDateAfter(LocalDateTime creationDate);
}