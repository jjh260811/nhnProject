package com.example.demo.controller;

import com.example.demo.domain.Project;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "projects")
public class ProjectController {

    @GetMapping
    public List<Project> getAllProjects() {
        return null;
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId) {
        return null;
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return null;
    }

    @PutMapping
    public Project updateProject(@RequestBody Project project) {
        return null;
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {

    }
}
