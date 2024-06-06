package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity

public class Project {
    public enum ProjectStatus {
        ACTIVE,
        DORMANT,
        ENDED;

        @JsonCreator
        public static ProjectStatus jsonCreator(String str) {
            for (ProjectStatus value : values()) {
                if (value.name().equalsIgnoreCase(str)) {
                    return value;
                }
            }
            return ACTIVE;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @NotNull
    @Setter
    private String projectName;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Setter
    private ProjectStatus projectStatus;



    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Milestone> milestones= new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    public Project(String projectName, ProjectStatus projectStatus){
        this.projectName = projectName;
        this.projectStatus = projectStatus;
    }
}
