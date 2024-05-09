package com.sentryc.repository.specification;

import com.sentryc.domain.Sellers;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SellerSpecificationsTest {

    @Test
    void hasName() {
        String name = "example";
        Specification<Sellers> specification = SellerSpecifications.hasName(name);
        assertNotNull(specification);
    }

    @Test
    void hasProducerIds() {
        List<UUID> producerIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        Specification<Sellers> specification = SellerSpecifications.hasProducerIds(producerIds);
        assertNotNull(specification);
    }

    @Test
    void hasMarketplaceIds() {
        List<String> marketplaceIds = Arrays.asList("marketplace1", "marketplace2");
        Specification<Sellers> specification = SellerSpecifications.hasMarketplaceIds(marketplaceIds);
        assertNotNull(specification);
    }
}
