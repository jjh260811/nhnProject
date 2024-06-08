package com.example.demo.controller;

import com.example.demo.dto.ProjectCreateDto;
import com.example.demo.dto.ProjectGetByUserIdRequestDto;
import com.example.demo.dto.ProjectGetDto;
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
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @GetMapping
    public List<Project> getAllProjects(@RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size,
                                        @RequestParam(required = false) Integer sort,
                                        @RequestBody ProjectGetByUserIdRequestDto request) {
        return projectRepository.findProjectsByUserId(request.userId());
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null){
            throw new RuntimeException();
        }

        return project;
    }

    @PostMapping
    public ProjectCreateDto createProject(@RequestBody ProjectCreateDto request) {
        Project project = new Project(
                request.name(),
                request.status(),
                request.memberIds()
        );

        // Create admin member
        MemberPk adminMemberPk = new MemberPk(request.adminUserId(), project.getProjectId());
        Member adminMember = new Member(adminMemberPk, Member.MemberRole.ADMIN);
        project.addMember(adminMember);

        // Create other members
        for (Long memberId : request.memberIds()) {
            MemberPk memberPk = new MemberPk(memberId, project.getProjectId());
            Member member = new Member(memberPk, Member.MemberRole.MEMBER);
            project.addMember(member);
        }


        projectRepository.save(project);
        projectRepository.flush();

        ProjectCreateDto response = ProjectCreateDto.builder()
                .name(project.getProjectName())
                .status(project.getProjectStatus())
                .memberIds(project.get)
                .build();

         memberRepository.save(new Member(new MemberPk(request.adminUserId(), project.getProjectId()), project, Member.MemberRole.ADMIN));
         return response;
    }

//    @PutMapping("/{projectId}")
//    public void updateProject(@PathVariable Long projectId, @RequestBody UpdateProjectRequest project) {
//        projectRepository.updateProjectByProjectId(projectId, project.projectName(), project.projectStatus());
//    }
//
//    @DeleteMapping("/{projectId}")
//    public void deleteProject(@PathVariable Long projectId) {
//        Project project = projectRepository.findById(projectId).orElse(null);
//
//        if(project == null){
//            throw new RuntimeException();
//        }
//
//        projectRepository.deleteById(projectId);
//    }
}
