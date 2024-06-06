package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull
    private String commentContent;

    @ManyToOne(optional = false)
    private Task task;

    public Comment(String commentContent, Task task) {
        this.commentContent = commentContent;
        this.task = task;
    }
}
