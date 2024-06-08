package com.example.demo.milestone.milestonedto;

import java.time.ZonedDateTime;


public record MilestoneCreateRequestDto(
                                      String milestoneName,
                                      ZonedDateTime milestoneStartDate,
                                      ZonedDateTime milestoneEndDate){}
