package com.sentryc.domain;


import com.sentryc.graphql.enums.SellerState;

import java.util.UUID;

public class ProducerSellerState {
    private UUID producerId;
    private String producerName;
    private SellerState sellerState;
    private UUID sellerId;

    public ProducerSellerState(UUID producerId, String producerName, SellerState sellerState, UUID sellerId) {
        this.producerId = producerId;
        this.producerName = producerName;
        this.sellerState = sellerState;
        this.sellerId = sellerId;
    }

    public UUID getProducerId() {
        return producerId;
    }

    public void setProducerId(UUID producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public SellerState getSellerState() {
        return sellerState;
    }

    public void setSellerState(SellerState sellerState) {
        this.sellerState = sellerState;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }
}
