package com.example.demo.milestone.milestonedto;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record MilestoneCreateDto(String milestoneName, ZonedDateTime milestoneStartDate, ZonedDateTime milestoneEndDate) {
}
