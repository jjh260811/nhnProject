package com.example.demo.controller;


import com.example.demo.domain.Milestone;
import com.example.demo.repository.MilestoneRepository;
import com.example.demo.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping
    public List<Milestone> getAllMilestones() {
        return null;
    }

    @GetMapping("/{milestoneId}")
    public Milestone getMilestone(@PathVariable Long milestoneId) {
        return null;
    }

    @PostMapping
    public void createMilestone(@RequestBody Milestone milestone) {

    }

    @PutMapping
    public void updateMilestone(@RequestBody Milestone milestone) {

    }

    @DeleteMapping
    public void deleteMilestone(@RequestBody Milestone milestone) {
        
    }
}
