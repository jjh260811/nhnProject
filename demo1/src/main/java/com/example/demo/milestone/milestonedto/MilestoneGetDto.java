package com.example.demo.milestone.milestonedto;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record MilestoneGetDto( String milestoneName,
                               ZonedDateTime milestoneStartDate,
                               ZonedDateTime milestoneEndDate) {

}
