package br.com.gabrielferreira.ai.domain.services.impl;

import br.com.gabrielferreira.ai.domain.model.ImageDataModel;
import br.com.gabrielferreira.ai.domain.services.ImageService;
import br.com.gabrielferreira.ai.infrastructure.configs.properties.AIProperties;
import br.com.gabrielferreira.ai.util.ImageMimeTypeDetector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageModel imageModel;
    private final AIProperties aiProperties;

    @Override
    public ImageDataModel image(String message) {
        log.info("Generating image for prompt: {}", message);
        AIProperties.ImageProperties imageProperties = aiProperties.image();
        var imagePrompt = new ImagePrompt(
                message,
                OpenAiImageOptions.builder()
                        .quality(imageProperties.quality())
                        .n(imageProperties.numberOfImages())
                        .height(imageProperties.height())
                        .width(imageProperties.width())
                        .build());
        ImageResponse imageResponse = imageModel.call(imagePrompt);
        List<ImageGeneration> imageGenerationList = imageResponse.getResults();
        if (CollectionUtils.isEmpty(imageGenerationList)) {
            // TODO: CRIAR UMA MENSAGEM EXCEPTION PERSONALIZADA
            log.error("No images generated for prompt: {}", message);
            throw new IllegalArgumentException("No images generated");
        }

        ImageGeneration imageGeneration = imageGenerationList.get(0);
        String imageBase64 = imageGeneration.getOutput().getB64Json();
        log.info("Generated image for prompt: {}", message);
        return ImageDataModel.builder()
                .mimeType(ImageMimeTypeDetector.detectMimeType(imageBase64))
                .base64(imageBase64)
                .build();
    }
}
