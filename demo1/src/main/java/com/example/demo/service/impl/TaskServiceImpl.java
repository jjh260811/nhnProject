package com.example.demo.service.impl;

import com.example.demo.dto.TaskCreateDto;
import com.example.demo.dto.TaskReadResponseDto;
import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.request.UpdateTaskRequest;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<TaskReadResponseDto> findAllTask(Long projectId) {

        List<TaskReadResponseDto> tasks = new ArrayList<>();
        for(Task task : taskRepository.findAllByProjectProjectId(projectId)){
            TaskReadResponseDto taskReadResponseDto = TaskReadResponseDto.builder()
                    .task(task)
                    .build();
            tasks.add(taskReadResponseDto);
        }
        return tasks;
    }

    @Override
    public TaskReadResponseDto getById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task(id = " + taskId + ") not found."));

        return TaskReadResponseDto.builder()
                .task(task)
                .build();
    }

    @Override
    public TaskCreateDto create(TaskCreateDto request, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project(id = " + projectId + ") not found."));
        Milestone milestone = milestoneRepository.findById(request.milestoneId()).orElse(null);

        Task task = new Task(
                request.name(),
                request.description(),
                request.status(),
                project,
                milestone
        );

        taskRepository.save(task);

        return TaskCreateDto.builder()
                .name(task.getTaskName())
                .description(task.getTaskDescription())
                .status(task.getTaskStatus())
                .milestoneId(task.getMilestone().getMilestoneId())
                .build();

    }

    @Override
    public void modifyById(UpdateTaskRequest request, Long taskId) {
        if(!taskRepository.existsById(taskId)){
            throw new IllegalArgumentException("Task id " + taskId + " not exists.");
        }

        Milestone milestone = milestoneRepository.findById(request.milestoneId()).orElse(null);

        taskRepository.updateByTaskId(
                taskId,
                request.taskName(),
                request.taskDescription(),
                request.taskStatus(),
                milestone);
    }

    @Override
    public void deleteById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new IllegalArgumentException("Task id " + id + " not exists.");
        }
        taskRepository.deleteById(id);
    }
}
