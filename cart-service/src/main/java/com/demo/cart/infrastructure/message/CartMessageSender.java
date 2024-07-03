package com.demo.cart.infrastructure.message;

import com.demo.cart.application.command.model.ItemDto;
import com.demo.cart.application.command.model.VasItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CartMessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CartMessageSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendItem(String topic, ItemDto itemDto) {

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(itemDto);
            kafkaTemplate.send(topic, json);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendVasItem(String topic, VasItemDto vasItemDto) {

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(vasItemDto);
            kafkaTemplate.send(topic, json);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendDeleteItemToCart(String topic, Long id) {
        kafkaTemplate.send(topic,id.toString());
    }

    public void sendRemoveAllItemFromCart(String topic) {
        kafkaTemplate.send(topic,"remove all item");
    }
}
