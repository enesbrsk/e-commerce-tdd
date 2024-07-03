package com.demo.promotion.application.command.service;

import com.demo.item.application.command.model.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TotalPricePromotionService {

    private static final BigDecimal BETWEEN_500_AND_5000_DISCOUNT = new BigDecimal("250");
    private static final BigDecimal BETWEEN_5000_AND_10000_DISCOUNT = new BigDecimal("500");
    private static final BigDecimal BETWEEN_10000_AND_50000_DISCOUNT = new BigDecimal("1000");
    private static final BigDecimal ABOVE_50000_DISCOUNT = new BigDecimal("2000");


    public  BigDecimal promotion(BigDecimal totalAmount) {
        if (isBetween500And5000(totalAmount)) {
            return BETWEEN_500_AND_5000_DISCOUNT;
        } else if (isBetween5000And10000(totalAmount)) {
            return BETWEEN_5000_AND_10000_DISCOUNT;
        } else if (isBetween10000And50000(totalAmount)) {
            return BETWEEN_10000_AND_50000_DISCOUNT;
        } else if (isAbove50000(totalAmount)) {
            return ABOVE_50000_DISCOUNT;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isBetween500And5000(BigDecimal totalPrice) {
        return isBetween(totalPrice, BigDecimal.valueOf(500), BigDecimal.valueOf(5000));
    }

    private boolean isBetween5000And10000(BigDecimal totalPrice) {
        return isBetween(totalPrice, BigDecimal.valueOf(5000), BigDecimal.valueOf(10000));
    }

    private boolean isBetween10000And50000(BigDecimal totalPrice) {
        return isBetween(totalPrice, BigDecimal.valueOf(10000), BigDecimal.valueOf(50000));
    }

    private boolean isAbove50000(BigDecimal totalPrice) {
        return totalPrice.compareTo(BigDecimal.valueOf(50000)) >= 0;
    }

    private boolean isBetween(BigDecimal value, BigDecimal lower, BigDecimal upper) {
        return value.compareTo(lower) >= 0 && value.compareTo(upper) < 0;
    }

}
