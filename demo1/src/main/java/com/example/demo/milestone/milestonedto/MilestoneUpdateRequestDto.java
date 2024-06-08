package com.example.demo.milestone.milestonedto;


import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record MilestoneUpdateRequestDto(
        Long milestoneId,
        String milestoneName,
        ZonedDateTime milestoneStartDate,
        ZonedDateTime milestoneEndDate){}
