package com.example.demo.dto;

import com.example.demo.entity.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TaskCreateResponseDto(String name,
                                    String description,
                                    @NotBlank Task.TaskStatus status,
                                    long projectId,
                                    long milestoneId) {
}
