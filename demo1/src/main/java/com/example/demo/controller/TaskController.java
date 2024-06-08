package com.example.demo.controller;

import com.example.demo.dto.TaskCreateDto;
import com.example.demo.dto.TaskAllReadRequestDto;
import com.example.demo.dto.TaskReadResponseDto;
import com.example.demo.entity.Task;
import com.example.demo.request.UpdateTaskRequest;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskReadResponseDto>> getTasks(@RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer size,
                                              @RequestParam(required = false) Integer sort,
                                              @RequestBody TaskAllReadRequestDto request) {
        List<TaskReadResponseDto> tasks = taskService.findAllTask(request.projectId());

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskReadResponseDto> getTask(@PathVariable Long taskId) {
        TaskReadResponseDto taskReadResponseDto = taskService.getById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(taskReadResponseDto);
    }

    @PostMapping
    public ResponseEntity<TaskCreateDto> createTask(@RequestBody TaskCreateDto request, @PathVariable Long projectId) {
        TaskCreateDto taskCreateDto = taskService.create(request, projectId);
        return ResponseEntity.status(HttpStatus.OK).body(taskCreateDto);
    }

    @PutMapping("/{taskId}")
    public void updateTask(@RequestBody UpdateTaskRequest request, @PathVariable Long taskId) {
        taskService.modifyById(request, taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteById(taskId);
    }

}
