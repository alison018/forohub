package com.forhub.api.controller;


import com.forhub.api.dto.status.StatusResponse;
import com.forhub.api.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<StatusResponse> getStatusValues() {
        StatusResponse response = new StatusResponse("Success", Status.values());
        return ResponseEntity.ok(response);
    }
}
