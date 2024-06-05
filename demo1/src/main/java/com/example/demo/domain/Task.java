package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotNull
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne
    private Milestone milestone;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "task tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags;

    public enum Status {
        TODO, IN_PROGRESS, DONE
    }
}
