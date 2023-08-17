package com.challenge.inditex.service;

import com.challenge.inditex.prizing.model.Price;
import com.challenge.inditex.prizing.repository.PriceRepository;
import com.challenge.inditex.prizing.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPricesWithMultipleMatchingPrices() {
        String brandId = "1";
        String productId = "35455";
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");

        List<Price> priceList = new ArrayList<>();
        Price price1 = new Price();
        priceList.add(price1);

        Price price2 = new Price();
        priceList.add(price2);

        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, date, date)).thenReturn(priceList);

        priceService.getPrices(brandId, productId, date);

        verify(priceRepository, times(1)).findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, date, date);
    }

    @Test
    void testGetPricesWithNoMatchingPrices() {
        String brandId = "1";
        String productId = "35455";
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");

        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, date, date)).thenReturn(new ArrayList<>());

        priceService.getPrices(brandId, productId, date);

        verify(priceRepository, times(1)).findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, date, date);
    }

}

