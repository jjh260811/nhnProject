package com.example.demo.service;

import com.example.demo.dto.TaskCreateDto;
import com.example.demo.dto.TaskReadResponseDto;
import com.example.demo.entity.Task;
import com.example.demo.request.UpdateTaskRequest;

import java.util.List;

public interface TaskService {
    List<TaskReadResponseDto> findAllTask(Long projectId);

    TaskReadResponseDto getById(Long taskId);

    TaskCreateDto create(TaskCreateDto request, Long projectId);

    void modifyById(UpdateTaskRequest updateTaskRequest, Long taskId);

    void deleteById(Long id);
}
