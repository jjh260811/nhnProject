package com.example.demo.response;

import com.example.demo.entity.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateProjectResponse(@NotNull String projectName,
                                    @Enumerated(EnumType.STRING) @NotNull Project.ProjectStatus projectStatus,
                                    List<Task> tasks,
                                    List<Member> members,
                                    List<Milestone> milestones,
                                    List<Tag> tags) {

}
