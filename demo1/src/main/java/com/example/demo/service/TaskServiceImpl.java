package com.example.demo.service;

import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.impl.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task(id = " + id + ") not found."));
    }

    @Override
    public Task create(String name, String description, Task.TaskStatus status, Project project, Milestone milestone) {
        return taskRepository.save(new Task(name, description, status, project, milestone));
    }

    @Override
    public Task modifyById(Long taskId, String name, String description, Task.TaskStatus status, Long milestoneId) {
        if(!taskRepository.existsById(taskId)){
            throw new IllegalArgumentException("Task id " + taskId + " not exists.");
        }
        return null;
//        return taskRepository.updateByTaskId(taskId, name, description, status, milestoneId);
    }

    @Override
    public void deleteById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new IllegalArgumentException("Task id " + id + " not exists.");
        }
        taskRepository.deleteById(id);
    }
}
