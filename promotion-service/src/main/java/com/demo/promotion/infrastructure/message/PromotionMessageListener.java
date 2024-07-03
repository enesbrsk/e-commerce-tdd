package com.demo.promotion.infrastructure.message;

import com.demo.item.application.command.model.dto.ItemDto;
import com.demo.promotion.application.command.service.PromotionCommandService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PromotionMessageListener {

    private final PromotionCommandService promotionCommandService;

    public PromotionMessageListener(PromotionCommandService promotionCommandService) {
        this.promotionCommandService = promotionCommandService;
    }

    @KafkaListener(topics = "added-item-to-cart", groupId = "test")
    public void vasItemHandle(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            System.out.println(String.format("Received Message: %s ", message));
            List<ItemDto> itemDtoList = mapper.readValue(message, new TypeReference<List<ItemDto>>() {});

            promotionCommandService.calculationCart(itemDtoList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
