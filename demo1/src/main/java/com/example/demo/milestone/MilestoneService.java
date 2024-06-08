package com.example.demo.milestone;

import com.example.demo.milestone.milestonedto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MilestoneService {
    List<MilestoneGetDto> getMilestonesByProjectId(Long projectId);
    void createMilestoneById(MilestoneCreateRequestDto createMilestoneRequest, Long projectId);
    void changeMilestoneById(MilestoneUpdateRequestDto createMilestoneRequest, Long projectId);
    void deleteMilestoneById(MilestoneGetByMilestoneIdRequestDto milestoneRequest);
}
