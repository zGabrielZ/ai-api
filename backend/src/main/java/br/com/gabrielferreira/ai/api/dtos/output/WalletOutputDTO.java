package br.com.gabrielferreira.ai.api.dtos.output;

import br.com.gabrielferreira.ai.domain.enums.AssetType;
import br.com.gabrielferreira.ai.domain.enums.Market;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Builder
public record WalletOutputDTO(
        String summary,
        BigDecimal totalValue,
        List<PositionOutputDTO> positions
) implements Serializable {

    @Override
    public List<PositionOutputDTO> positions() {
        return positions == null ? Collections.emptyList() : positions;
    }

    @Builder
    public record PositionOutputDTO(
            String ticker,
            AssetType assetType,
            Market market,
            BigDecimal quantity,
            BigDecimal averagePrice,
            BigDecimal currentPrice,
            BigDecimal marketValue,
            BigDecimal variation
    ) implements Serializable {
    }
}
