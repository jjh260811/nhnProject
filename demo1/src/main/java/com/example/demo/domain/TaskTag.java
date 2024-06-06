package com.example.demo.domain;

import jakarta.persistence.EmbeddedId;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class TaskTag {

    @EmbeddedId
    TaskTagPk taskTagPk;


}
