package com.example.demo.request;

import com.example.demo.entity.*;
import com.example.demo.milestone.entity.Milestone;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record CreateProjectRequest(@NotNull String projectName,
                                   @Enumerated(EnumType.STRING) @NotNull Project.ProjectStatus projectStatus,
                                   List<Task> tasks,
                                   List<Member> members,
                                   List<Milestone> milestones,
                                   List<Tag> tags){
}
