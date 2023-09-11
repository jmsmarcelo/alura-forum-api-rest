package com.github.jmsmarcelo.alura.forum.api.domain.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryData(@NotBlank String name) {
}
