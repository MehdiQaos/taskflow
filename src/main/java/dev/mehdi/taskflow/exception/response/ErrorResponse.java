package dev.mehdi.taskflow.exception.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String message;
    private final String details;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
