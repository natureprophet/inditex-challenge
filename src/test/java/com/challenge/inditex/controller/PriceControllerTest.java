package com.challenge.inditex.controller;

import com.challenge.inditex.prizing.controller.PriceController;
import com.challenge.inditex.prizing.model.Price;
import com.challenge.inditex.prizing.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        priceController.modelMapper = new ModelMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    void testGetPrices() throws Exception {
        String date = "2020-06-14T10:00:00";
        String productId = "35455";
        String brandId = "1";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        List<Price> priceList = new ArrayList<>();
        Price price1 = new Price();
        // Set up price1 object according to your needs
        priceList.add(price1);

        Price price2 = new Price();
        // Set up price2 object according to your needs
        priceList.add(price2);

        when(priceService.getPrices(brandId, productId, dateTime)).thenReturn(priceList);

        mockMvc.perform(get("/api/prices")
                        .param("date", date)
                        .param("productId", productId)
                        .param("brandId", brandId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // Assuming priceList contains 2 prices

        verify(priceService, times(1)).getPrices(brandId, productId, dateTime);
    }


    @Test
    void testGetPricesWithNoMatchingPrices() throws Exception {
        String date = "2020-06-14T10:00:00";
        String productId = "35455";
        String brandId = "1";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        when(priceService.getPrices(brandId, productId, dateTime)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/prices")
                        .param("date", date)
                        .param("productId", productId)
                        .param("brandId", brandId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0)); // Expecting an empty result

        verify(priceService, times(1)).getPrices(brandId, productId, dateTime);
    }
}

