package br.com.gabrielferreira.ai.domain.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ImageDataModel(
        String mimeType,
        String base64
) implements Serializable {
}
