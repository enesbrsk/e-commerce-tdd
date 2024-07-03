package com.demo.item.application.command.service;

import com.demo.item.application.command.model.dto.ItemDto;

public abstract class ItemService {

    public abstract void addItemToCart(ItemDto itemDto);

}
