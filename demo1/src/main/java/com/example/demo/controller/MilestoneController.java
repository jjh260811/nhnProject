package com.example.demo.controller;


import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.request.CreateMilestoneRequest;
import com.example.demo.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{projectId}/milestones")
public class MilestoneController {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;


    @GetMapping
    public ModelAndView getAllMilestones(@PathVariable Long projectId) {
        ModelAndView modelAndView = new ModelAndView("milestone");
        modelAndView.addObject("milestones", milestoneRepository.findAllByProjectProjectId(projectId));
        return modelAndView;
    }

    @GetMapping("/{milestoneId}")
    public ModelAndView getMilestone(@PathVariable("projectId") String projectId, @PathVariable Long milestoneId) {
        ModelAndView modelAndView = new ModelAndView("milestoneId");
        modelAndView.addObject("milestone", milestoneRepository.findById(milestoneId).orElse(null));
        return modelAndView;
    }

    @PostMapping
    public Milestone createMilestone(@RequestBody CreateMilestoneRequest createMilestoneRequest, @PathVariable("projectId") Long projectId) {
                Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        Milestone milestone = new Milestone(createMilestoneRequest.milestoneName(),createMilestoneRequest.milestoneProgress(), project);
        milestoneRepository.save(milestone);
        milestoneRepository.flush();

        return milestone;
    }

    @PutMapping("/{milestoneId}")
    public void updateMilestone(@PathVariable("projectId") Long projectId,@PathVariable("milestoneId") Long milestoneId, @RequestBody CreateMilestoneRequest milestone) {
        milestoneRepository.updateProjectByProjectId(projectId,milestoneId,milestone.milestoneName(),milestone.milestoneProgress(),milestone.milestoneStartDate(),milestone.milestoneEndDate());

    }

    @DeleteMapping("/{milestoneId}")
    public void deleteMilestone(@PathVariable("projectId") Long projectId,@PathVariable Long milestoneId) {
        if(milestoneRepository.existsById(milestoneId)) {
            milestoneRepository.deleteById(milestoneId);
            milestoneRepository.flush();
        }
        else{
            throw new IllegalArgumentException("Invalid milestone ID");
        }

        
    }
}
