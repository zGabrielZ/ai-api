package br.com.gabrielferreira.ai.api.controllers;

import br.com.gabrielferreira.ai.api.dtos.input.ChatInputDTO;
import br.com.gabrielferreira.ai.api.dtos.input.ImageInputDTO;
import br.com.gabrielferreira.ai.api.dtos.input.WalletInputDTO;
import br.com.gabrielferreira.ai.api.dtos.output.ChatOutputDTO;
import br.com.gabrielferreira.ai.api.dtos.output.ImageOutputDTO;
import br.com.gabrielferreira.ai.api.dtos.output.WalletOutputDTO;
import br.com.gabrielferreira.ai.domain.enums.AssetType;
import br.com.gabrielferreira.ai.domain.enums.Market;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/chat")
    public ResponseEntity<ChatOutputDTO> chat(@RequestBody @Valid ChatInputDTO chatInputDTO) {
        return ResponseEntity.ok(ChatOutputDTO.builder().response(chatInputDTO.message()).build());
    }

    @PostMapping("/images")
    public ResponseEntity<ImageOutputDTO> images(@RequestBody @Valid ImageInputDTO imageInputDTO) {
        return ResponseEntity.ok(ImageOutputDTO.builder().mimeType("image/jpeg").base64("base64encodedimage").build());
    }

    @PostMapping("/wallet")
    public ResponseEntity<WalletOutputDTO> wallet(@RequestBody @Valid WalletInputDTO walletInputDTO) {
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
