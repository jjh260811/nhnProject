package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private ZonedDateTime milestoneStartDate;

    private ZonedDateTime milestoneEndDate;

    @ManyToOne(optional = false)
    private Project project;

    public Milestone(String milestoneName, ZonedDateTime milestoneStartDate, ZonedDateTime milestoneEndDate,Project project) {
        this.milestoneName = milestoneName;
        this.milestoneStartDate = milestoneStartDate;
        this.milestoneEndDate = milestoneEndDate;
        this.project = project;
    }

}
