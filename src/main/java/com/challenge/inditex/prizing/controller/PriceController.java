package com.challenge.inditex.prizing.controller;

import com.challenge.inditex.prizing.dto.PriceDTO;
import com.challenge.inditex.prizing.service.PriceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    public ModelMapper modelMapper;

    final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public List<PriceDTO> getPrices(
            @RequestParam String date,
            @RequestParam String productId,
            @RequestParam String brandId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return priceService.getPrices(
                brandId, productId, dateTime).stream()
                .map(game -> modelMapper.map(game, PriceDTO.class))
                .collect(Collectors.toList());
    }
}
