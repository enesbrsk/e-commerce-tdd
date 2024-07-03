package com.demo.item.infrastructure.message;

import com.demo.item.domain.entities.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMessageSender {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public ItemMessageSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendItemToPromotion(String topic,List<Item> item) {

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(item);
            kafkaTemplate.send(topic, json);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendError(String topic,String message){
        System.out.println("error sending");
            kafkaTemplate.send(topic, message);
    }

}
