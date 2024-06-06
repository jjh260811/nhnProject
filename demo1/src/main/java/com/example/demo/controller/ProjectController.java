package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.request.CreateProjectRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public ModelAndView getAllProjects() {
        ModelAndView modelAndView = new ModelAndView("projectList");
        List<Project> projects = projectRepository.findAll();
        modelAndView.addObject("projects", projects);

        return modelAndView;
    }

    @GetMapping("/{projectId}")
    public ModelAndView getProject(@PathVariable Long projectId) {
        ModelAndView modelAndView = new ModelAndView("projectDetail");
        Project project =projectRepository.findById(projectId).orElse(null);
        if(project == null){
            throw new RuntimeException();
        }
        modelAndView.addObject("project", project);

        return modelAndView;
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
         projectRepository.save(project);
         projectRepository.flush();
        return project;

    }

    @PutMapping("/{projectId}")
    public void updateProject(@PathVariable Long projectId,@RequestBody CreateProjectRequest project) {
        projectRepository.updateProjectByProjectId(projectId, project.projectName(), project.projectStatus());

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
