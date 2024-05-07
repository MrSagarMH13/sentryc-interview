package com.sentryc.graphql.resolver;

import com.sentryc.domain.ProducerSellerState;
import com.sentryc.domain.Sellers;
import com.sentryc.graphql.enums.SellerSortBy;
import com.sentryc.graphql.filters.PageInput;
import com.sentryc.graphql.filters.SellerFilter;
import com.sentryc.service.SellersService;
import com.sentryc.service.dto.AppResponseDTO;
import com.sentryc.service.dto.PageMeta;
import com.sentryc.service.dto.SellerResponseDTO;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SellerQueryResolver implements GraphQLQueryResolver {
    private final SellersService sellerService;

    public SellerQueryResolver(SellersService sellerService) {
        this.sellerService = sellerService;
    }

    public AppResponseDTO<SellerResponseDTO> sellers(SellerFilter filter, PageInput pageInput, SellerSortBy sortBy) {
        Sort sort = switch (sortBy) {
            case NAME_ASC -> Sort.by("sellerInfo.name").ascending();
            case NAME_DESC -> Sort.by("sellerInfo.name").descending();
            case SELLER_INFO_EXTERNAL_ID_ASC -> Sort.by("sellerInfo.externalId").ascending();
            case SELLER_INFO_EXTERNAL_ID_DESC -> Sort.by("sellerInfo.externalId").descending();
            case MARKETPLACE_ID_ASC -> Sort.by("sellerInfo.marketplace.id").ascending();
            case MARKETPLACE_ID_DESC -> Sort.by("sellerInfo.marketplace.id").descending();
        };

        Pageable pageable = PageRequest.of(pageInput.getPage(), pageInput.getSize(), sort);

        Page<Sellers> sellerPage = sellerService.findSellers(filter, pageable);

        AppResponseDTO<SellerResponseDTO> response = new AppResponseDTO<>();
        response.setData(mapSellersResponse(sellerPage.getContent()));

        PageMeta pageMeta = new PageMeta(
                sellerPage.getNumber(),
                sellerPage.getSize(),
                (int) sellerPage.getTotalElements(),
                sellerPage.getTotalPages()
        );

        response.setMeta(pageMeta);

        return response;
    }

    private List<SellerResponseDTO> mapSellersResponse(List<Sellers> sellers) {
        // Group sellers by sellerInfo id
        Map<UUID, List<Sellers>> sellersGroupedBySellerInfoId = sellers.stream()
                .filter(seller -> seller.getSellerInfo() != null)
                .collect(Collectors.groupingBy(seller -> seller.getSellerInfo().getId()));

        return sellersGroupedBySellerInfoId.values().stream()
                .map(sellersList -> {
                    SellerResponseDTO dto = new SellerResponseDTO();

                    // All sellers in current group have same sellerInfo. So, fetch details from first seller
                    Sellers firstSeller = sellersList.get(0);

                    dto.setSellerName(firstSeller.getSellerName());
                    dto.setExternalId(firstSeller.getExternalId());
                    dto.setMarketplaceId(UUID.fromString(firstSeller.getMarketplaceId()));

                    List<ProducerSellerState> producerSellerStates = sellersList.stream()
                            .map(seller -> new ProducerSellerState(seller.getProducerId(), seller.getProducerName(), seller.getState(), seller.getId()))
                            .collect(Collectors.toList());

                    dto.setProducerSellerStates(producerSellerStates);

                    return dto;
                })
                .toList();
    }
}
