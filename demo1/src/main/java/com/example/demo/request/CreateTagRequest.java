package com.example.demo.request;

import jakarta.validation.constraints.NotNull;

public record CreateTagRequest(@NotNull String tagName) {}
