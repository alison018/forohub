package com.forhub.api.service;

import com.forhub.api.dto.topic.CreateTopicDTO;
import com.forhub.api.dto.topic.TopicDTO;
import com.forhub.api.dto.topic.UpdateTopicDTO;
import com.forhub.api.infra.errores.DuplicateEntityException;
import com.forhub.api.model.Course;
import com.forhub.api.model.Status;
import com.forhub.api.model.Topic;
import com.forhub.api.model.User;
import com.forhub.api.repository.CourseRepository;
import com.forhub.api.repository.TopicRepository;
import com.forhub.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public TopicDTO createTopic(CreateTopicDTO createTopicDTO) {
        if (topicRepository.existsByTitleAndMessage(createTopicDTO.title(), createTopicDTO.message())) {
            throw new DuplicateEntityException("Duplicate topic");
        }

        User author = userRepository.findById(createTopicDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        Course course = courseRepository.findById(createTopicDTO.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        Topic topic = new Topic();
        topic.setTitle(createTopicDTO.title());
        topic.setMessage(createTopicDTO.message());
        topic.setAuthor(author);
        topic.setCourse(course);
        topic.setStatus(Status.OPEN);
        topicRepository.save(topic);
        return mapToDTO(topic);
    }

    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<TopicDTO> getTop10Topics() {
        return topicRepository.findTop10ByOrderByCreationDateAsc()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Page<TopicDTO> getTopicsByCourseAndYear(Long courseId, String year, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(year + "-01-01", formatter);
        LocalDate endDate = LocalDate.parse(year + "-12-31", formatter);
        Page<Topic> topics = topicRepository.findByCourseAndCreationDateBetween(courseId, startDate.toString(), endDate.toString(), pageable);
        return topics.map(this::mapToDTO);
    }

    public TopicDTO getTopicById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        return mapToDTO(topic);
    }

    public TopicDTO updateTopic(Long id, UpdateTopicDTO updateTopicDTO) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        Course course = courseRepository.findById(updateTopicDTO.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        User author = userRepository.findById(updateTopicDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        // Check for duplicates
        if (topicRepository.existsByTitleAndMessage(updateTopicDTO.title(), updateTopicDTO.message()) &&
                !topic.getTitle().equals(updateTopicDTO.title()) &&
                !topic.getMessage().equals(updateTopicDTO.message())) {
            throw new DuplicateEntityException("Duplicate topic");
        }

        topic.setTitle(updateTopicDTO.title());
        topic.setMessage(updateTopicDTO.message());
        topic.setAuthor(author);
        topic.setCourse(course);
        topic.setStatus(updateTopicDTO.status());
        topicRepository.save(topic);
        return mapToDTO(topic);
    }

    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }

    private TopicDTO mapToDTO(Topic topic) {
        return new TopicDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor().getId(),
                topic.getCourse().getId()
        );
    }
}
