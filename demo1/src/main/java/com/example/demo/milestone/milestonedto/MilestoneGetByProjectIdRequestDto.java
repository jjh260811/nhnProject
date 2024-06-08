package com.example.demo.milestone.milestonedto;

import lombok.Builder;

@Builder
public record MilestoneGetByProjectIdRequestDto(long projectId) {
}
