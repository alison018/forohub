package com.forhub.api.controller;

import com.forhub.api.dto.response.CreateResponseDTO;
import com.forhub.api.dto.response.ResponseDTO;
import com.forhub.api.service.ResponseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createResponse(@Valid @RequestBody CreateResponseDTO createResponseDTO) {
        ResponseDTO newResponse = responseService.createResponse(createResponseDTO);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllResponses() {
        List<ResponseDTO> responses = responseService.getAllResponses();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable Long id) {
        ResponseDTO response = responseService.getResponseById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable Long id, @Valid @RequestBody CreateResponseDTO updateResponseDTO) {
        ResponseDTO updatedResponse = responseService.updateResponse(id, updateResponseDTO);
        return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        responseService.deleteResponse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
