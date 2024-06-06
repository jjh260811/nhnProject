package com.example.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "project")
    private List<Milestone> milestones;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @OneToMany(mappedBy = "project")
    private List<Tag> tags;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMembers;


}
