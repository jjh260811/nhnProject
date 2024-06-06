package com.example.demo.controller;


import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.request.CreateMilestoneRequest;
import com.example.demo.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{projectId}/milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @GetMapping
    public List<Milestone> getAllMilestones(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size,
                                            @RequestParam(required = false) Integer sort ) {
        return milestoneRepository.findAll();
    }

    @GetMapping("/{milestoneId}")
    public Milestone getMilestone(@PathVariable("projectId") String projectId, @PathVariable Long milestoneId) {
        return milestoneRepository.findById(milestoneId).orElse(null);
    }

    @PostMapping
    public Milestone createMilestone(@RequestBody CreateMilestoneRequest createMilestoneRequest, @PathVariable("projectId") Long projectId) {
                Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        Milestone milestone = new Milestone(createMilestoneRequest.milestoneName(),createMilestoneRequest.milestoneProgress(), project);
        return milestoneRepository.save(milestone);
    }

    @PutMapping
    public void updateMilestone(@PathVariable("projectId") Long projectId, @RequestBody Milestone milestone) {

    }

    @DeleteMapping
    public void deleteMilestone(@PathVariable("projectId") Long projectId, @RequestBody Milestone milestone) {
        
    }
}
