package com.example.demo.controller;

import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.request.CreateTaskRequest;
import com.example.demo.request.UpdateTaskRequest;
import com.example.demo.service.TaskServiceImpl;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users/{userId}/projects/{projectId}/tasks")
public class TaskController {
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TaskRepository taskRepository;

    @GetMapping("/add")
    public ModelAndView addTaskView(@PathVariable Long userId, @PathVariable Long projectId) {
        ModelAndView modelAndView = new ModelAndView("taskAddView");
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("projectId", projectId);


        return modelAndView;
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size,
                               @RequestParam(required = false) Integer sort,
                               @PathVariable Long projectId) {

        return taskRepository.findAllByProjectProjectId(projectId);
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return taskRepository.findByProjectProjectIdAndTaskId(projectId, taskId);
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest createTaskRequest, @PathVariable("projectId") Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        Milestone milestone = null;
        if (createTaskRequest.milestoneId() != null) {
            milestone = milestoneRepository.findById(createTaskRequest.milestoneId())
                    .orElse(null);
        }

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
