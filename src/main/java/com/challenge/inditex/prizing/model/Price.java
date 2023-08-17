package com.challenge.inditex.prizing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="BRAND_ID")
    private String brandId;

    @Column(name="START_DATE")
    private LocalDateTime startDate;

    @Column(name="END_DATE")
    private LocalDateTime endDate;

    @Column(name="PRICE_LIST")
    private String priceList;

    @Column(name="PRODUCT_ID")
    private String productId;

    @Column(name="PRIORITY")
    private int priority;

    @Column(name = "PRICE", columnDefinition = "DECIMAL(20, 2)")
    private BigDecimal price;

    @Column(name="CURR")
    private String currency;
}
