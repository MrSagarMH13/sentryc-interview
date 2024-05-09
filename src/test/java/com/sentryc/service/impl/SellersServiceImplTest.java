package com.sentryc.service.impl;

import com.sentryc.domain.Sellers;
import com.sentryc.graphql.filters.SellerFilter;
import com.sentryc.repository.SellersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SellersServiceImplTest {
    @Mock
    private SellersRepository sellersRepository;
    @InjectMocks
    private SellersServiceImpl sellersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindSellers() {
        String argument1 = "someString";
        List<UUID> producerIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        List<String> marketplaceIds = Arrays.asList("marketplaceId1", "marketplaceId2");
        SellerFilter filter = new SellerFilter(argument1, producerIds, marketplaceIds);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Sellers> mockPage = mock(Page.class);
        when(sellersRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(mockPage);
        Page<Sellers> result = sellersService.findSellers(filter, pageable);
        ArgumentCaptor<Specification<Sellers>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
        verify(sellersRepository).findAll(specificationCaptor.capture(), any(Pageable.class));
        Specification<Sellers> capturedSpecification = specificationCaptor.getValue();
    }

    @Test
    void testFindSellers_NullFilter() {
        SellerFilter filter = null;
        Pageable pageable = PageRequest.of(0, 10);
        assertThrows(NullPointerException.class, () -> sellersService.findSellers(filter, pageable));
    }

    @Test
    void testFindSellers_EmptyResult() {
        SellerFilter filter = new SellerFilter("someString", Arrays.asList(UUID.randomUUID(), UUID.randomUUID()), Arrays.asList("marketplaceId1", "marketplaceId2"));
        Pageable pageable = PageRequest.of(0, 10);
        when(sellersRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Page.empty());
        Page<Sellers> result = sellersService.findSellers(filter, pageable);
        assertTrue(result.isEmpty());
    }
}
