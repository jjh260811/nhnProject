package com.example.demo.dto;

import com.example.demo.entity.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TaskCreateDto(String name, String description, @NotBlank Task.TaskStatus status, long milestoneId) {
}
