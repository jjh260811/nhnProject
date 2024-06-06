package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    @NotNull
    private String milestoneName;

    @NotNull
    private Integer milestoneProgress;


    private ZonedDateTime milestoneStartDate;


    private ZonedDateTime milestoneEndDate;

    @ManyToOne(optional = false)
    private Project project;

//    @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Task> tasks;


    public Milestone(String milestoneName, Integer milestoneProgress, Project project) {
        this.milestoneName = milestoneName;
        this.milestoneProgress = milestoneProgress;
        this.project = project;
    }
}
