package com.github.jmsmarcelo.alura.forum.api.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseDataUpdate(@NotNull Long id, @NotBlank String message, @NotNull Long topicId) {

}
