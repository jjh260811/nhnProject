package com.example.demo.entity;

import com.example.demo.milestone.entity.Milestone;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Task {
    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE;

        @JsonCreator
        public static TaskStatus jsonCreator(String str) {
            for (TaskStatus value : values()) {
                if (value.name().equalsIgnoreCase(str)) {
                    return value;
                }
            }
            return TODO;
        }
    }

    public Task(String taskName, String taskDescription, TaskStatus taskStatus, Project project, Milestone milestone) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.project = project;
        this.milestone = milestone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotNull
    private String taskName;

    private String taskDescription;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatus taskStatus;

    @ManyToOne
    @NotNull
    private Project project;

    @OneToOne
    private Milestone milestone;


}
