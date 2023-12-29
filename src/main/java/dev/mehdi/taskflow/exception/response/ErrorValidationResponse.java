package dev.mehdi.taskflow.exception.response;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorValidationResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message = "Validation Failed";
    private final String details;
    private final Map<String, String> errors;

    public ErrorValidationResponse(String details , Map<String, String> errors) {
        this.details = details;
        this.errors = errors;
    }
}
