package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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

    private ZonedDateTime projectStartDate;

    private ZonedDateTime projectEndDate;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Milestone> milestones= new ArrayList<>();
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Task> tasks = new ArrayList<>();
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Tag> tags = new ArrayList<>();
//
    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> projectMembers = new ArrayList<>();

    public Project(String projectName, ProjectStatus projectStatus, String projectStartDate, String projectEndDate){
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.projectStartDate = ZonedDateTime.parse(projectStartDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        this.projectEndDate = ZonedDateTime.parse(projectEndDate,DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
    public String getStartDateToString(){
        return projectStartDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm"));
    }
    public String getEndDateToString(){
        return projectEndDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm"));
    }
}
