package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ProjectMember {
    public enum ProjectMemberRole {
        ADMIN,
        MEMBER;

        @JsonCreator
        public static ProjectMemberRole jsonCreator(String str) {
            for (ProjectMemberRole value : values()) {
                if (value.name().equalsIgnoreCase(str)) {
                    return value;
                }
            }
            return MEMBER;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectMemberId;

//    @ManyToOne
//    @JoinColumn(name = "member_id", nullable = false)
//    private User member;

    @ManyToOne
    @NotNull
    private Project project;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProjectMemberRole projectMemberRole;
}
