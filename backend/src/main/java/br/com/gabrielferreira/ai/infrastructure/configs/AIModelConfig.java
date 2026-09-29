package br.com.gabrielferreira.ai.infrastructure.configs;

import br.com.gabrielferreira.ai.infrastructure.configs.properties.AIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AIModelConfig {

    private final AIProperties aiProperties;

    @Bean
    @Primary
    public ChatModel activeChatModel(
            OpenAiChatModel openAiChatModel,
            DeepSeekChatModel deepSeekChatModel,
            OllamaChatModel ollamaChatModel
    ) {

        return switch (aiProperties.chat().provider()) {
            case OPENAI -> openAiChatModel;
            case DEEPSEEK -> deepSeekChatModel;
            case OLLAMA -> ollamaChatModel;
        };
    }
}
