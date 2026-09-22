package br.com.gabrielferreira.ai.api.controllers;

import br.com.gabrielferreira.ai.api.dtos.request.ChatRequest;
import br.com.gabrielferreira.ai.api.dtos.response.ChatResponse;
import br.com.gabrielferreira.ai.api.dtos.request.ImageRequest;
import br.com.gabrielferreira.ai.api.dtos.response.ImageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ai")
@RequiredArgsConstructor
public class GenerativeAIController {

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody @Valid ChatRequest chatRequest) {
        return ResponseEntity.ok(ChatResponse.builder().response(chatRequest.message()).build());
    }

    @PostMapping("/images")
    public ResponseEntity<ImageResponse> images(@RequestBody @Valid ImageRequest imageRequest) {
        return ResponseEntity.ok(ImageResponse.builder().mimeType("image/jpeg").base64("base64encodedimage").build());
    }

}
