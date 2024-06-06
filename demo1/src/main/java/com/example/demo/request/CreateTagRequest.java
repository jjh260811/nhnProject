package com.example.demo.request;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;


public record CreateTagRequest(@NotNull String tagName,
                               Long projectId) {}
