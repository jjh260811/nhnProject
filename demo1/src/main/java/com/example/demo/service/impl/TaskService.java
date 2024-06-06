package com.example.demo.service.impl;

import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;

public interface TaskService {
    Task getById(Long id);

    Task create(String name, String description, Task.TaskStatus status, Project project, Milestone milestone);

    Task modifyById(Long taskId, String name, String description, Task.TaskStatus status, Long milestoneId);

    void deleteById(Long id);
}
