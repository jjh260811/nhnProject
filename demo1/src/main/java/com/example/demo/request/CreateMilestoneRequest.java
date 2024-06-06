package com.example.demo.request;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMilestoneRequest {

    @NotNull
    private String milestoneName;

    @NotNull
    private Integer milestoneProgress;
}
