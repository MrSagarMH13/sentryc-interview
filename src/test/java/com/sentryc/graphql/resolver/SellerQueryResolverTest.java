package com.sentryc.graphql.resolver;

import com.sentryc.domain.*;
import com.sentryc.graphql.enums.SellerSortBy;
import com.sentryc.graphql.enums.SellerState;
import com.sentryc.graphql.filters.PageInput;
import com.sentryc.graphql.filters.SellerFilter;
import com.sentryc.service.SellersService;
import com.sentryc.service.dto.AppResponseDTO;
import com.sentryc.service.dto.SellerResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerQueryResolverTest {
    @InjectMocks
    private SellerQueryResolver sellerQueryResolver;
    @Mock
    private SellersService sellerService;

    @BeforeEach
    public void setUp() {
        sellerQueryResolver = new SellerQueryResolver(sellerService);
    }

    @Test
    public void testSellers() {
        // Create mock objects
        String marketplaceId = UUID.randomUUID().toString();
        Marketplaces mockMarketplace = new Marketplaces();
        mockMarketplace.setId(marketplaceId);

        SellerInfos mockSellerInfo = new SellerInfos();
        mockSellerInfo.setId(UUID.randomUUID());
        mockSellerInfo.setName("sellerName1");
        mockSellerInfo.setExternalId("externalId1");
        mockSellerInfo.setMarketplace(mockMarketplace);

        Producers mockProducer = new Producers();
        mockProducer.setId(UUID.randomUUID());
        mockProducer.setName("producerName1");

        Sellers mockSeller1 = new Sellers();
        mockSeller1.setId(UUID.randomUUID());
        mockSeller1.setState(SellerState.BLACKLISTED);
        mockSeller1.setSellerInfo(mockSellerInfo);
        mockSeller1.setProducer(mockProducer);

        Page<Sellers> mockSellerPage = new PageImpl<>(Collections.singletonList(mockSeller1));

        SellerFilter filter = new SellerFilter("sellerName1", null, null);
        PageInput pageInput = new PageInput();
        pageInput.setPage(0);
        pageInput.setSize(10);
        SellerSortBy sortBy = SellerSortBy.NAME_ASC;

        // When
        when(sellerService.findSellers(any(SellerFilter.class), any(Pageable.class))).thenReturn(mockSellerPage);

        // Action
        AppResponseDTO<SellerResponseDTO> response = sellerQueryResolver.sellers(filter, pageInput, sortBy);

        // Verify and Assert
        verify(sellerService).findSellers(any(SellerFilter.class), any(Pageable.class));

        assertEquals(0, response.getMeta().getPage());
        assertEquals(1, response.getMeta().getSize());
        assertEquals(1, response.getMeta().getTotalElements());
        assertEquals(1, response.getMeta().getTotalPages());
        assertEquals(1, response.getData().size());

        SellerResponseDTO firstDto = response.getData().get(0);
        assertEquals("sellerName1", firstDto.getSellerName());
        assertEquals("externalId1", firstDto.getExternalId());
        assertEquals(UUID.fromString(marketplaceId), firstDto.getMarketplaceId());
    }
}