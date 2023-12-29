package dev.mehdi.taskflow.dto.task;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskRequestDto {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    @Min(value = 1, message = "Status id must be greater than 0")
    private Integer statusId;

    @Future(message = "Start date must be in the future")
    private LocalDateTime startDate;

    @Future(message = "End date must be in the future or present")
    private LocalDateTime endDate;

    @NotNull(message = "Project is required")
    @Min(value = 1, message = "Project id must be greater than 0")
    private Long projectId;

    @NotNull(message = "Creator is required")
    private Long creatorMembershipId;

    @NotEmpty(message = "at least one tag is required")
    private List<Long> tagsIds = new ArrayList<>();
}