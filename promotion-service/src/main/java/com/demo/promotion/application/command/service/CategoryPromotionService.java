package com.demo.promotion.application.command.service;

import com.demo.item.application.command.model.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryPromotionService {

    private static final BigDecimal DISCOUNT = new BigDecimal("0.5");

    public BigDecimal promotion(List<ItemDto> itemDtoList){

        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (ItemDto itemDto : itemDtoList){
            if(itemDto.getCategoryId() == 3003){
                totalDiscount.add(itemDto.getPrice().multiply(DISCOUNT).multiply(BigDecimal.valueOf(itemDto.getQuantity())));
            }
        }
        return totalDiscount;
    }


}
