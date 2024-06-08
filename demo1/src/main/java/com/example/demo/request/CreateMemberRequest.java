package com.example.demo.request;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberPk;
import com.example.demo.entity.Project;
import jakarta.validation.constraints.NotNull;

public record CreateMemberRequest(@NotNull MemberPk memberPk,
                                  @NotNull Member.MemberRole memberRole) {
}
