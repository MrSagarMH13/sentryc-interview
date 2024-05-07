package com.sentryc.repository.specification;

import com.sentryc.domain.Sellers;
import com.sentryc.graphql.filters.SellerFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class SellerSpecifications {
    public static Specification<Sellers> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            Join<?, ?> sellerInfo = root.join("sellerInfo", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(sellerInfo.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Sellers> hasProducerIds(List<UUID> producerIds) {
        return (root, query, criteriaBuilder) ->
                root.join("producer", JoinType.LEFT).get("id").in(producerIds);
    }

    public static Specification<Sellers> hasMarketplaceIds(List<String> marketplaceIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("sellerInfo", JoinType.LEFT);
            return join.join("marketplace", JoinType.LEFT).get("id").in(marketplaceIds);
        };
    }

    public static Specification<Sellers> build(SellerFilter filter) {
        Specification<Sellers> spec = Specification.where(null);
        if (filter.getSearchByName() != null) {
            spec = spec.and(hasName(filter.getSearchByName()));
        }
        if (filter.getProducerIds() != null && !filter.getProducerIds().isEmpty()) {
            spec = spec.and(hasProducerIds(filter.getProducerIds()));
        }
        if (filter.getMarketplaceIds() != null && !filter.getMarketplaceIds().isEmpty()) {
            spec = spec.and(hasMarketplaceIds(filter.getMarketplaceIds()));
        }
        return spec;
    }
}