package com.challenge.inditex.prizing.service;

import com.challenge.inditex.prizing.model.Price;
import com.challenge.inditex.prizing.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {

    final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getPrices(
            String brandId,
            String productId,
            LocalDateTime date) {
        return priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, date, date);
    }
}
