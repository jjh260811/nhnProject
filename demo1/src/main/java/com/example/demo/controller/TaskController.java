package com.example.demo.controller;

import com.example.demo.dto.TaskCreateRequestDto;
import com.example.demo.dto.TaskGetByUserIdRequestDto;
import com.example.demo.entity.Milestone;
import com.example.demo.entity.Task;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.request.UpdateTaskRequest;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{projectId}/tasks")
public class TaskController {
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size,
                               @RequestParam(required = false) Integer sort,
                               @RequestBody TaskGetByUserIdRequestDto request) {

        return taskService.findAllTask(request.projectId());
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return taskService.getById(taskId);
    }

    @PostMapping
    public Task createTask(@RequestBody TaskCreateRequestDto request, @PathVariable Long projectId) {
        return taskService.create(request, projectId);

    }

    @PutMapping("/{taskId}")
    public void updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable("projectId") Long projectId, @PathVariable Long taskId) {

        Milestone milestone = null;
        if (updateTaskRequest.milestoneId() != null) {
            milestone = milestoneRepository.findById(updateTaskRequest.milestoneId())
                    .orElse(null);
        }

        taskRepository.updateByTaskId(taskId, updateTaskRequest.taskName(), updateTaskRequest.taskDescription(), Task.TaskStatus.jsonCreator(String.valueOf(updateTaskRequest.taskStatus())), milestone);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        }
    }

}
