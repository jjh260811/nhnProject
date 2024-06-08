package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class TaskTag {
    @EmbeddedId
    TaskTagPk taskTagPk;

    @JsonBackReference
    @ManyToOne(optional = false)
    @MapsId("taskId")
    private Task task;

    @JsonBackReference
    @ManyToOne(optional = false)
    @MapsId("tagId")
    private Tag tag;
}
