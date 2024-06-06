package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getAllProjects(Model model) {

        return projectRepository.findAll();
    }

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new RuntimeException();
        }
        model.addAttribute("project", project);



        return "projectDetail";
    }

    @PostMapping
    public String createProject(@RequestBody Project project) {
        projectRepository.save(project);
        return "redirect:/projects/"+project.getProjectId();
    }

    @PutMapping
    public String updateProject(@RequestBody Project project) {
        Project project2 = projectRepository.findById(project.getProjectId()).orElse(null);
        if(project2 == null){
            throw new RuntimeException();
        }
        projectRepository.delete(project2);
        projectRepository.save(project2);
        return "redirect:/projects";
    }

    @DeleteMapping("/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
        Project project2 = projectRepository.findById(projectId).orElse(null);
        if(project2 == null){
            throw new RuntimeException();
        }
        projectRepository.deleteById(projectId);
        return "redirect:/projects";
    }
}
