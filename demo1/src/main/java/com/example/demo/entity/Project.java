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
    @Setter
    private ProjectStatus projectStatus;


    public Project(String projectName, ProjectStatus projectStatus){
        this.projectName = projectName;
        this.projectStatus = projectStatus;
    }


}
