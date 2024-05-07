package com.sentryc.service.impl;

import com.sentryc.domain.Sellers;
import com.sentryc.graphql.filters.SellerFilter;
import com.sentryc.repository.SellersRepository;
import com.sentryc.repository.specification.SellerSpecifications;
import com.sentryc.service.SellersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SellersServiceImpl implements SellersService {

    private final SellersRepository sellersRepository;

    public SellersServiceImpl(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @Override
    public Page<Sellers> findSellers(SellerFilter filter, Pageable pageable) {
        Specification<Sellers> spec = SellerSpecifications.build(filter);
        return sellersRepository.findAll(spec, pageable);
    }
}
