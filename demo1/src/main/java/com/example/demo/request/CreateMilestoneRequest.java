package com.example.demo.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;


public record CreateMilestoneRequest(@NotNull String milestoneName, @NotNull Integer milestoneProgress){}
