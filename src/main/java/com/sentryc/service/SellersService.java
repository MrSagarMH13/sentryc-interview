package com.sentryc.service;

import com.sentryc.domain.Sellers;
import com.sentryc.graphql.filters.SellerFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Sellers}.
 */
public interface SellersService {
    Page<Sellers> findSellers(SellerFilter filter, Pageable pageable);
}
