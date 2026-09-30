package br.com.gabrielferreira.ai.infrastructure.configs;

import br.com.gabrielferreira.ai.infrastructure.configs.properties.AIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AIImageConfig {

    private final AIProperties aiProperties;

    @Bean
    @Primary
    public ImageModel activeImageModel(
            OpenAiImageModel openAiImageModel
    ) {
        return switch (aiProperties.image().provider()) {
            case OPENAI -> openAiImageModel;
        };
    }
}
