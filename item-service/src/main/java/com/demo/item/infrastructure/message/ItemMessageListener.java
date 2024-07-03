package com.demo.item.infrastructure.message;

import com.demo.item.application.command.model.dto.ItemDto;
import com.demo.item.application.command.model.dto.VasItemDto;
import com.demo.item.application.command.service.implemantation.DefaultItemServiceImpl;
import com.demo.item.application.command.service.implemantation.DigitalItemServiceImpl;
import com.demo.item.application.command.service.implemantation.VasItemServiceImpl;
import com.demo.item.domain.enums.ItemType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ItemMessageListener {

    private final DefaultItemServiceImpl defaultItemServiceImpl;
    private final DigitalItemServiceImpl digitalItemServiceImpl;
    private final VasItemServiceImpl vasItemServiceImpl;

    public ItemMessageListener(DefaultItemServiceImpl defaultItemServiceImpl, DigitalItemServiceImpl digitalItemServiceImpl, VasItemServiceImpl vasItemServiceImpl) {
        this.defaultItemServiceImpl = defaultItemServiceImpl;
        this.digitalItemServiceImpl = digitalItemServiceImpl;
        this.vasItemServiceImpl = vasItemServiceImpl;
    }


    @KafkaListener(topics = "create-cart-item", groupId = "test")
    public void itemHandle(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(String.format("Received Message: [%s] ", message));
            ItemDto itemDto = mapper.readValue(message, ItemDto.class);

            switch (itemDto.getCommand()) {
                case DIGITAL_ITEM:
                    digitalItemServiceImpl.addItemToCart(itemDto);
                    break;
                case DEFAULT_ITEM:
                    defaultItemServiceImpl.addItemToCart(itemDto);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid item type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "create-cart-vasItem", groupId = "test")
    public void vasItemHandle(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(String.format("Received Message: [%s] ", message));
            VasItemDto  vasItemDto = mapper.readValue(message, VasItemDto.class);

            vasItemServiceImpl.addVasItemToCart(vasItemDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "delete-item-from-cart", groupId = "test")
    public void removeItemFromCartHandle(String id) {
            System.out.println(String.format("Received Message: [%s] ", id));
            defaultItemServiceImpl.removeItemFromCartByItemId(Long.valueOf(id));
    }

    @KafkaListener(topics = "remove-all-item-from-cart", groupId = "test")
    public void removeAllItemFromCartHandle(String message) {
        System.out.println(String.format("Received Message: [%s] ", message));
        defaultItemServiceImpl.removeAllItemFromCart();
    }


}
