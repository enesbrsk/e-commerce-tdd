package com.demo.promotion.application.command.service;

import com.demo.item.application.command.model.dto.ItemDto;
import com.demo.promotion.application.command.model.dto.CartDto;
import com.demo.promotion.application.command.model.dto.VasItemDto;
import com.demo.promotion.infrastructure.message.PromotionMessageSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class PromotionCommandService {

    private final SameSallerPromotionService sameSallerPromotionService;
    private final CategoryPromotionService categoryPromotionService;
    private final TotalPricePromotionService totalPricePromotionService;
    private final PromotionMessageSender promotionMessageSender;

    public  PromotionCommandService(SameSallerPromotionService sameSallerPromotionService,
                                    CategoryPromotionService categoryPromotionService,
                                    TotalPricePromotionService totalPricePromotionService,
                                    PromotionMessageSender promotionMessageSender) {
        this.sameSallerPromotionService = sameSallerPromotionService;
        this.categoryPromotionService = categoryPromotionService;
        this.totalPricePromotionService = totalPricePromotionService;
        this.promotionMessageSender = promotionMessageSender;
    }


    public void calculationCart(List<ItemDto> itemDtoList){



        BigDecimal totalAmount = totalAmountItem(itemDtoList);

        BigDecimal samePromotionDiscountAmount = sameSallerPromotionService.promotion(itemDtoList,totalAmountItem(itemDtoList));
        BigDecimal categoryPromotionDiscountAmount = categoryPromotionService.promotion(itemDtoList);
        BigDecimal totalPromotionDiscountAmount = totalPricePromotionService.promotion(totalAmount);

        HashMap<String,BigDecimal> promotionSelect = new HashMap<String,BigDecimal>();
        promotionSelect.put("SameSellerPromotion",samePromotionDiscountAmount);
        promotionSelect.put("CategoryPromotion",categoryPromotionDiscountAmount);
        promotionSelect.put("TotalPricePromotion",totalPromotionDiscountAmount);


        HashMap<String, Object> maxDiscountInfo = findMaxDiscount(promotionSelect);

        BigDecimal maxDiscount = (BigDecimal) maxDiscountInfo.get("maxDiscount");
        String maxDiscountType = (String) maxDiscountInfo.get("maxDiscountType");

        totalAmount = totalAmount.add(totalAmountVasItem(itemDtoList));


        int totalQuantity = itemDtoList.stream()
                .mapToInt(ItemDto::getQuantity)
                .sum();


        CartDto cartDto;
        if (!itemDtoList.isEmpty()){
            cartDto = new CartDto(itemDtoList.get(0).getCartId(), totalAmount,maxDiscount, totalQuantity);
        }else {
            cartDto = new CartDto(itemDtoList.get(0).getCartId(), totalAmount,maxDiscount, totalQuantity);
        }

        promotionMessageSender.updateCart("update-cart",cartDto);


    }

    private BigDecimal totalAmountItem(List<ItemDto> itemDtoList){
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (ItemDto itemDto : itemDtoList) {

            BigDecimal price = new BigDecimal(String.valueOf(itemDto.getPrice()));

            totalRevenue = totalRevenue.add(price.multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }
        return totalRevenue;
    }

    private BigDecimal totalAmountVasItem(List<ItemDto> itemDtoList){
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (ItemDto itemDto : itemDtoList) {

            for (VasItemDto vasItemDto : itemDto.getVasItem()) {

                BigDecimal price = new BigDecimal(vasItemDto.getPrice());

                totalRevenue = totalRevenue.add(price.multiply(BigDecimal.valueOf(vasItemDto.getQuantity())));
            }

        }
        return totalRevenue;
    }

    private HashMap<String, Object> findMaxDiscount(HashMap<String, BigDecimal> promotionSelect) {
        BigDecimal maxDiscount = BigDecimal.ZERO;
        String maxDiscountType = "";

        for (HashMap.Entry<String, BigDecimal> entry : promotionSelect.entrySet()) {
            BigDecimal discount = entry.getValue();
            if (discount.compareTo(maxDiscount) > 0) {
                maxDiscount = discount;
                maxDiscountType = entry.getKey();
            }
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("maxDiscount", maxDiscount);
        result.put("maxDiscountType", maxDiscountType);

        return result;
    }


}
