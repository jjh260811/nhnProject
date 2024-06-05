package com.example.demo.controller;

import com.example.demo.domain.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "projects/{ProjectId}/Tasks")
public class TaskController {

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
