package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.request.CreateProjectRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId) {

        Project project =projectRepository.findById(projectId).orElse(null);
        if(project == null){
            throw new RuntimeException();
        }

        return project;
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);

    }

    @PutMapping
    public void updateProject(@RequestBody Project project) {
        Project project2 = projectRepository.findById(project.getProjectId()).orElse(null);
        if(project2 == null){
            throw new RuntimeException();
        }
        projectRepository.delete(project2);
        projectRepository.save(project2);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        Project project2 = projectRepository.findById(projectId).orElse(null);
        if(project2 == null){
            throw new RuntimeException();
        }
        projectRepository.deleteById(projectId);
    }
}
