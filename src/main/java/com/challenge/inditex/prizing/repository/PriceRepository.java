package com.challenge.inditex.prizing.repository;

import com.challenge.inditex.prizing.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
    List<Price> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            String brandId, String productId, LocalDateTime startDate, LocalDateTime endDate);
}
