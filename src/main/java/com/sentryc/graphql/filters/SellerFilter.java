package com.sentryc.graphql.filters;

import java.util.List;
import java.util.UUID;

public class SellerFilter {
    private String searchByName;
    private List<UUID> producerIds;
    private List<String> marketplaceIds;

    // Constructors, getters, and setters
    public SellerFilter(String searchByName, List<UUID> producerIds, List<String> marketplaceIds) {
        this.searchByName = searchByName;
        this.producerIds = producerIds;
        this.marketplaceIds = marketplaceIds;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public List<UUID> getProducerIds() {
        return producerIds;
    }

    public void setProducerIds(List<UUID> producerIds) {
        this.producerIds = producerIds;
    }

    public List<String> getMarketplaceIds() {
        return marketplaceIds;
    }

    public void setMarketplaceIds(List<String> marketplaceIds) {
        this.marketplaceIds = marketplaceIds;
    }
}
