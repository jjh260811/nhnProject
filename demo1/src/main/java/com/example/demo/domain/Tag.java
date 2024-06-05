package com.example.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @NotNull
    private String name;

    @ManyToOne(optional = false)
    private Project project;

    @OneToMany(mappedBy = "task tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
