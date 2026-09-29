package br.com.gabrielferreira.ai.domain.services.impl;

import br.com.gabrielferreira.ai.domain.services.ChatService;
import br.com.gabrielferreira.ai.infrastructure.configs.properties.AIProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatModel chatModel;
    private final AIProperties aiProperties;

    @Override
    public String chat(String message) {
        log.info("Sending message to {}: {}", message, aiProperties.chat().provider());
        var prompt = new Prompt(message);
        ChatResponse response = chatModel.call(prompt);
        String result = Objects.requireNonNull(response.getResult()).getOutput().getText();
        log.info("Received response from {}: {}", aiProperties.chat().provider(), result);
        return result;
    }
}
