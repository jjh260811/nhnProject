package com.example.demo.controller;

import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.request.CreateTaskRequest;
import com.example.demo.service.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{projectId}/tasks")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> getTasks(@PathVariable Long projectId) {
        return null;
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return taskServiceImpl.getById(projectId);
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest createTaskRequest, @PathVariable("projectId") Long projectId) {

//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
        Milestone milestone = milestoneRepository.findById(createTaskRequest.milestoneId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid milestone ID"));

        Project project = projectRepository.save(new Project("testName", Project.ProjectStatus.ACTIVE));
        // Task 객체 생성
        Task task = new Task(
                createTaskRequest.taskName(),
                createTaskRequest.taskDescription(),
                Task.TaskStatus.jsonCreator(String.valueOf(createTaskRequest.taskStatus())),
                project,
                milestone
        );

        return taskRepository.save(task);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task, @PathVariable("projectId") Long projectId) {
        return null;
    }

    @DeleteMapping("/{taskId}")
    public Task deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return null;
    }

}
