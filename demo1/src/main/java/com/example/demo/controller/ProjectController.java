package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberPk;
import com.example.demo.entity.Project;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.request.CreateProjectRequest;
import com.example.demo.request.UpdateProjectRequest;
import com.example.demo.response.CreateProjectResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @GetMapping
    public List<Project> getAllProjects(@PathVariable Long userId) {
        return projectRepository.findProjectsByUserId(userId);
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long userId, @PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null){
            throw new RuntimeException();
        }

        return project;
    }

    @PostMapping
    public CreateProjectResponse createProject(@RequestBody CreateProjectRequest request, @PathVariable Long userId) {
        Project project = new Project(
                request.projectName(),
                request.projectStatus(),
                request.tasks(),
                request.members(),
                request.milestones(),
                request.tags()
        );

        projectRepository.save(project);
        projectRepository.flush();

        CreateProjectResponse response = CreateProjectResponse.builder()
                .projectName(project.getProjectName())
                .projectStatus(project.getProjectStatus())
                .tasks(project.getTasks())
                .members(project.getMembers())
                .milestones(project.getMilestones())
                .tags(project.getTags())
                .build();

         memberRepository.save(new Member(new MemberPk(userId, project.getProjectId()), project, Member.MemberRole.ADMIN));
         return response;
    }

    @PutMapping("/{projectId}")
    public void updateProject(@PathVariable Long projectId, @RequestBody UpdateProjectRequest project) {
        projectRepository.updateProjectByProjectId(projectId, project.projectName(), project.projectStatus());
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null){
            throw new RuntimeException();
        }

        projectRepository.deleteById(projectId);
    }
}
