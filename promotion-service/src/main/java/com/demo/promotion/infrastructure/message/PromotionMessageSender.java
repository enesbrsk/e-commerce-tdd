package com.demo.promotion.infrastructure.message;

import com.demo.promotion.application.command.model.dto.CartDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PromotionMessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PromotionMessageSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void updateCart(String topic, CartDto cartDto) {

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(cartDto);
            kafkaTemplate.send(topic, json);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
