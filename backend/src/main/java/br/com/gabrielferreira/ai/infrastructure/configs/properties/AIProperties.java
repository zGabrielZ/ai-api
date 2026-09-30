package br.com.gabrielferreira.ai.infrastructure.configs.properties;

import br.com.gabrielferreira.ai.infrastructure.configs.enums.AIImageProvider;
import br.com.gabrielferreira.ai.infrastructure.configs.enums.AIProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@ConfigurationProperties(prefix = "ai")
@Validated
public record AIProperties(
        @NotNull
        @Valid
        ChatProperties chat,

        @NotNull
        @Valid
        ImageProperties image
) implements Serializable {

    public record ChatProperties(
            @NotNull
            AIProvider provider
    ) implements Serializable {
    }

    public record ImageProperties(
            @NotNull
            AIImageProvider provider,

            @NotBlank
            String quality,

            @NotNull
            Integer numberOfImages,

            @NotNull
            Integer height,

            @NotNull
            Integer width
    ) implements Serializable {
    }
}
