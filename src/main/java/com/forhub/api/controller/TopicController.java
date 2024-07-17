package com.forhub.api.controller;

import com.forhub.api.dto.topic.CreateTopicDTO;
import com.forhub.api.dto.topic.TopicDTO;
import com.forhub.api.dto.topic.UpdateTopicDTO;
import com.forhub.api.dto.topic.SearchTopicsRequest;
import com.forhub.api.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@Validated
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO) {
        TopicDTO newTopic = topicService.createTopic(createTopicDTO);
        return new ResponseEntity<>(newTopic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        List<TopicDTO> topics = topicService.getAllTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<TopicDTO>> getTop10Topics() {
        List<TopicDTO> topics = topicService.getTop10Topics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @PostMapping("/course/year")
    public ResponseEntity<Page<TopicDTO>> getTopicsByCourseAndYear(
            @RequestBody @Valid SearchTopicsRequest searchTopicsRequest,
            Pageable pageable) {
        Page<TopicDTO> topics = topicService.getTopicsByCourseAndYear(searchTopicsRequest.courseId(), searchTopicsRequest.year(), pageable);
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable @Valid Long id) {
        TopicDTO topic = topicService.getTopicById(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> updateTopic(
            @PathVariable @Valid Long id,
            @RequestBody @Valid UpdateTopicDTO updateTopicDTO) {
        TopicDTO updatedTopic = topicService.updateTopic(id, updateTopicDTO);
        return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable @Valid Long id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
