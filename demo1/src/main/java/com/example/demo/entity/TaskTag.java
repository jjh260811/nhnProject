package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class TaskTag {

    @EmbeddedId
    TaskTagPk taskTagPk;

    @ManyToOne(optional = false)
    @MapsId("taskId")
    private Task task;

    @ManyToOne(optional = false)
    @MapsId("tagId")
    private Tag tag;
}
