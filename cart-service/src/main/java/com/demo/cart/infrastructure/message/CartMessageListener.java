package com.demo.cart.infrastructure.message;

import com.demo.cart.application.command.model.CartDto;
import com.demo.cart.application.command.model.response.ResponseMessage;
import com.demo.cart.application.command.service.CartCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CartMessageListener {

    private final CartCommandService cartCommandService;

    public CartMessageListener(CartCommandService cartCommandService) {
        this.cartCommandService = cartCommandService;
    }

    @KafkaListener(topics = "error-from-item", groupId = "test")
    public void getError(String message) {
            System.out.println(String.format("Received Message: [%s] ", message));

        new ResponseMessage(false, message);

    }


    @KafkaListener(topics = "update-cart", groupId = "test")
    public void updateCart(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(String.format("Received Message: [%s] ", message));
            CartDto cartDto = mapper.readValue(message, CartDto.class);

            cartCommandService.addCart(cartDto);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
