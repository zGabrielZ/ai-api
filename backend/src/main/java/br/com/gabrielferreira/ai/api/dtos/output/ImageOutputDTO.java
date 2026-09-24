package br.com.gabrielferreira.ai.api.dtos.output;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ImageOutputDTO(
        String mimeType,
        String base64
) implements Serializable {
}
