package com.demo.promotion.application.command.service;

import com.demo.item.application.command.model.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SameSallerPromotionService {


    private static final BigDecimal DISCOUNT = new BigDecimal("0.10");

    public BigDecimal promotion(List<ItemDto> itemDtoList,BigDecimal totalAmount){

        boolean result = itemDtoList == null || itemDtoList.isEmpty() ||
                itemDtoList.stream()
                        .map(ItemDto::getSellerId)
                        .distinct()
                        .count() == 1;



        if (!result){
            return totalAmount;
        }

        return totalAmount.multiply(DISCOUNT);
    }

}
