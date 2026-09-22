package br.com.gabrielferreira.ai.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ImageRequest(
        @NotBlank
        String prompt
) implements Serializable {
}
