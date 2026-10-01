package br.com.gabrielferreira.ai.api.controllers;

import br.com.gabrielferreira.ai.api.dtos.input.ChatInputDTO;
import br.com.gabrielferreira.ai.api.dtos.input.ImageInputDTO;
import br.com.gabrielferreira.ai.api.dtos.output.ImageOutputDTO;
import br.com.gabrielferreira.ai.api.dtos.output.WalletOutputDTO;
import br.com.gabrielferreira.ai.domain.enums.AssetType;
import br.com.gabrielferreira.ai.domain.enums.Market;
import br.com.gabrielferreira.ai.domain.model.ImageDataModel;
import br.com.gabrielferreira.ai.domain.services.ChatService;
import br.com.gabrielferreira.ai.domain.services.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/ai")
@RequiredArgsConstructor
public class GenerativeAIController {

    private final ChatService chatService;

    private final ImageService imageService;

    // TODO: FAZER UMA TOOL CHAMANDO UMA API DE NOTICIAS, CASO A IA NAO SABE, ELA FAZ A BUSCA NESSA API DE NOTICIAS
    @PostMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> chat(@RequestBody @Valid ChatInputDTO chatInputDTO) {
        String chat = chatService.chat(chatInputDTO.message());
        return ResponseEntity.ok(chat);
    }

    @PostMapping("/images")
    public ResponseEntity<ImageOutputDTO> images(@RequestBody @Valid ImageInputDTO imageInputDTO) {
        ImageDataModel imageData = imageService.image(imageInputDTO.prompt());
        return ResponseEntity.ok(ImageOutputDTO.builder()
                .mimeType(imageData.mimeType())
                .base64(imageData.base64())
                .build());
    }

    // TODO: PROMPT TEMPLATE -> Como está minha carteira hoje 23-09-2026?
    // Colocar em ptbr e ingles
    @GetMapping("/wallet")
    public ResponseEntity<WalletOutputDTO> wallet() {
        return ResponseEntity.ok(WalletOutputDTO.builder()
                .summary("Sua carteira possui 9 ações, com valor total de R$ 85.420,00. No momento, ITUB4 apresenta a maior alta entre as posições consultadas.")
                .totalValue(new BigDecimal("85420.00"))
                .positions(List.of(
                        WalletOutputDTO.PositionOutputDTO.builder()
                                .ticker("ITUB4")
                                .assetType(AssetType.STOCK)
                                .market(Market.BR)
                                .quantity(BigDecimal.valueOf(100))
                                .averagePrice(new BigDecimal("35.20"))
                                .currentPrice(new BigDecimal("39.15"))
                                .marketValue(new BigDecimal("3915.00"))
                                .variation(new BigDecimal("11.22"))
                                .build(),
                        WalletOutputDTO.PositionOutputDTO.builder()
                                .ticker("BBDC4")
                                .assetType(AssetType.STOCK)
                                .market(Market.BR)
                                .quantity(BigDecimal.valueOf(100))
                                .averagePrice(new BigDecimal("14.80"))
                                .currentPrice(new BigDecimal("15.20"))
                                .marketValue(new BigDecimal("1520.00"))
                                .variation(new BigDecimal("2.70"))
                                .build()
                ))
                .build());
    }
}
