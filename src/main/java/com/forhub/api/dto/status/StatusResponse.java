package com.forhub.api.dto.status;

import com.forhub.api.model.Status;

public record StatusResponse(String message, Status[] statuses) {}