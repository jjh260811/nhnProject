package com.example.demo.controller;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "projects/{ProjectId}/Tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getTasks(@PathVariable Long ProjectId) {
        return null;
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long ProjectId, @PathVariable Long taskId) {
        return null;
    }

    @PostMapping
    public void createTask(@RequestBody Task task, @PathVariable("ProjectId") String projectId) {

    }

    @PutMapping
    public void updateTask(@RequestBody Task task, @PathVariable("ProjectId") String projectId) {

    }

    @DeleteMapping("{/taskId}")
    public void deleteTask(@PathVariable Long ProjectId, @PathVariable Long taskId) {

    }

}
