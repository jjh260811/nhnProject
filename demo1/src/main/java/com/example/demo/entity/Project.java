package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    private String projectName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProjectStatus projectStatus;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Milestone> milestones;
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Task> tasks;
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Tag> tags;
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProjectMember> projectMembers;

    public Project(String projectName, ProjectStatus projectStatus){
        this.projectName = projectName;
        this.projectStatus = projectStatus;
    }
}
