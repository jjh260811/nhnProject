package com.example.demo.service.impl;

import com.example.demo.dto.TaskCreateRequestDto;
import com.example.demo.dto.TaskCreateResponseDto;
import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<Task> findAllTask(Long projectId) {
        return taskRepository.findAllByProjectProjectId(projectId);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task(id = " + id + ") not found."));
    }

    @Override
    public Task create(TaskCreateRequestDto request, Long projectId) {
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

        TaskCreateResponseDto response = TaskCreateRequestDto.builder()
                .name(task.getTaskName())
                .description(task.getTaskDescription())
                .status(task.getTaskStatus())
                .projectID
        return

    }

    @Override
    public Task create(String name, String description, Task.TaskStatus status, Project project, Milestone milestone) {

//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
//
//        Milestone milestone = null;
//        if (createTaskRequest.milestoneId() != null) {
//            milestone = milestoneRepository.findById(createTaskRequest.milestoneId())
//                    .orElse(null);
//        }
//
//        // Task 객체 생성
//        Task task = new Task(
//                createTaskRequest.taskName(),
//                createTaskRequest.taskDescription(),
//                Task.TaskStatus.jsonCreator(String.valueOf(createTaskRequest.taskStatus())),
//                project,
//                milestone
//        );

        return taskRepository.save(task);
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
