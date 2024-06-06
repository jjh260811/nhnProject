package com.example.demo.request;

import com.example.demo.entity.Project;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequest {

    @NotNull
    private String projectName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Project.ProjectStatus projectStatus;
}
