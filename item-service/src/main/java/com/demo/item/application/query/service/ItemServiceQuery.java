package com.demo.item.application.query.service;

import com.demo.item.domain.entities.Item;
import com.demo.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceQuery {

    private final ItemRepository itemRepository;

    public ItemServiceQuery(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItemByCartId(String cartId){
        return itemRepository.findByCartId(cartId);
    }

}
