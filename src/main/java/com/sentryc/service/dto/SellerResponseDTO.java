package com.sentryc.service.dto;

import com.sentryc.domain.ProducerSellerState;

import java.util.List;
import java.util.UUID;

public class SellerResponseDTO {
    private String sellerName;
    private String externalId;
    private List<ProducerSellerState> producerSellerStates;
    private UUID marketplaceId;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<ProducerSellerState> getProducerSellerStates() {
        return producerSellerStates;
    }

    public void setProducerSellerStates(List<ProducerSellerState> producerSellerStates) {
        this.producerSellerStates = producerSellerStates;
    }

    public UUID getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(UUID marketplaceId) {
        this.marketplaceId = marketplaceId;
    }
}
