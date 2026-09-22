package br.com.gabrielferreira.ai.api.dtos.response;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ImageResponse(
        String mimeType,
        String base64
) implements Serializable {
}
