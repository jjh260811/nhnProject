package com.example.demo.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;


public record CreateMilestoneRequest(@NotNull String milestoneName, @NotNull Integer milestoneProgress, @NotNull
                                     ZonedDateTime milestoneStartDate, @NotNull ZonedDateTime milestoneEndDate){}
