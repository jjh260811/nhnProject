package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.request.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/members")
public class MemberController {
    private final MemberRepository memberRepository;
    @GetMapping
    public List<Member> getMembers(@PathVariable("projectId") Long projectId) {
        return memberRepository.findByProjectProjectId(projectId);
    }

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable("projectId") Long projectId, @PathVariable Long memberId){
        return memberRepository.findById(memberId).orElse(null);
    }

    @PostMapping
    public Member createMember(@PathVariable("projectId") Long projectId, @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable("projectId") Long projectId, @PathVariable("memberId") Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
