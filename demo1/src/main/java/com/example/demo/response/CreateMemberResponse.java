package com.example.demo.response;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberPk;
import com.example.demo.entity.Project;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateMemberResponse(@NotNull MemberPk memberPk,
                                   @NotNull Member.MemberRole memberRole) {
}
