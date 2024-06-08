package com.example.demo.dto;

import lombok.Builder;

@Builder
public record TaskGetByUserIdRequestDto(long projectId) {
}
