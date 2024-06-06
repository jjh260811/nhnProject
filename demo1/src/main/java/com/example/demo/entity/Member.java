package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member {
    public enum MemberRole {
        ADMIN,
        MEMBER;

        @JsonCreator
        public static Member.MemberRole jsonCreator(String str) {
            for (Member.MemberRole value : values()) {
                if (value.name().equalsIgnoreCase(str)) {
                    return value;
                }
            }
            return MEMBER;
        }
    }

    @EmbeddedId
    private MemberPk memberPk;

    @JsonBackReference
    @ManyToOne(optional = false)
    @MapsId("projectId")
    private Project project;

    @NotNull
    private MemberRole memberRole;
}
