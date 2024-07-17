package com.forhub.api.service;

import com.forhub.api.dto.response.CreateResponseDTO;
import com.forhub.api.dto.response.ResponseDTO;
import com.forhub.api.model.Response;
import com.forhub.api.model.Topic;
import com.forhub.api.model.User;
import com.forhub.api.repository.ResponseRepository;
import com.forhub.api.repository.TopicRepository;
import com.forhub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseDTO createResponse(CreateResponseDTO createResponseDTO) {
        Topic topic = topicRepository.findById(createResponseDTO.topicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        User author = userRepository.findById(createResponseDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Response response = new Response();
        response.setMessage(createResponseDTO.message());
        response.setTopic(topic);
        response.setAuthor(author);
        responseRepository.save(response);
        return mapToDTO(response);
    }

    public List<ResponseDTO> getAllResponses() {
        return responseRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ResponseDTO getResponseById(Long id) {
        Response response = responseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Response not found"));
        return mapToDTO(response);
    }

    public ResponseDTO updateResponse(Long id, CreateResponseDTO updateResponseDTO) {
        Response response = responseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Response not found"));
        Topic topic = topicRepository.findById(updateResponseDTO.topicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        User author = userRepository.findById(updateResponseDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        response.setMessage(updateResponseDTO.message());
        response.setTopic(topic);
        response.setAuthor(author);
        responseRepository.save(response);
        return mapToDTO(response);
    }

    public void deleteResponse(Long id) {
        Response response = responseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Response not found"));
        responseRepository.delete(response);
    }

    private ResponseDTO mapToDTO(Response response) {
        return new ResponseDTO(
                response.getId(),
                response.getMessage(),
                response.getCreationDate(),
                response.getTopic().getId(),
                response.getAuthor().getId()
        );
    }
}
