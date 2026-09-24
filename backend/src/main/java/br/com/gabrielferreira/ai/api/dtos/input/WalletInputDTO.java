package br.com.gabrielferreira.ai.api.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record WalletInputDTO(
        @NotBlank
        String message
) implements Serializable {
}
