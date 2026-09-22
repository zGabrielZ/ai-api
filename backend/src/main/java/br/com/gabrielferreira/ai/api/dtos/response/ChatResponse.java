package br.com.gabrielferreira.ai.api.dtos.response;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ChatResponse(
        String response
) implements Serializable {
}
