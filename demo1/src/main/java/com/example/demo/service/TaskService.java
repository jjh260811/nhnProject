package com.example.demo.service;

import com.example.demo.dto.TaskCreateRequestDto;
import com.example.demo.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTask(Long projectId);

    Task getById(Long taskId);

    Task create(TaskCreateRequestDto request, Long projectId);

    Task modifyById(Long taskId, String name, String description, Task.TaskStatus status, Long milestoneId);

    void deleteById(Long id);
}
