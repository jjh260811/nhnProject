package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberPk;
import com.example.demo.entity.Project;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.request.UpdateProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public ModelAndView createProjectView(@PathVariable Long userId) {
        ModelAndView modelAndView = new ModelAndView("projectAddView");
        modelAndView.addObject("userId", userId);

        // Project 상태 값을 모델에 추가
        List<Project.ProjectStatus> projectStatuses = Arrays.asList(Project.ProjectStatus.values());
        modelAndView.addObject("projectStatuses", projectStatuses);

        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllProjects(@PathVariable Long userId) {
        ModelAndView modelAndView = new ModelAndView("projectListView");
        List<Project> projects = projectRepository.findAll();
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("userId", userId);

        return modelAndView;
    }

    @GetMapping("/{projectId}")
    public ModelAndView getProject(@PathVariable Long userId, @PathVariable Long projectId) {
        ModelAndView modelAndView = new ModelAndView("projectView");
        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null){
            throw new RuntimeException();
        }

        modelAndView.addObject("project", project);
        modelAndView.addObject("userId", userId);

        return modelAndView;
    }

    @PostMapping
    public String createProject(@ModelAttribute @RequestBody Project project, @PathVariable Long userId) {
         projectRepository.save(project);
         projectRepository.flush();

         memberRepository.save(new Member(new MemberPk(userId, project.getProjectId()), project, Member.MemberRole.ADMIN));
         return "redirect:/users/" + userId + "/projects/" + project.getProjectId();

    }

    @PutMapping("/{projectId}")
    public void updateProject(@PathVariable Long projectId,@RequestBody UpdateProjectRequest project) {
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
