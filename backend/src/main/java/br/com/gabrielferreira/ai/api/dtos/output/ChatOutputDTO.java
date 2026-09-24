package br.com.gabrielferreira.ai.api.dtos.output;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ChatOutputDTO(
        String response
) implements Serializable {
}
