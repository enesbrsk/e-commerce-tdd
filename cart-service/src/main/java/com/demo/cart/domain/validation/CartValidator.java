package com.demo.cart.domain.validation;

import com.demo.cart.application.command.model.ItemDto;
import com.demo.cart.domain.constant.Constants;
import com.demo.cart.domain.entities.Cart;

import java.util.List;

public class CartValidator {

    public static boolean validateItemToCart(Cart cart, ItemDto itemDto){
        return true;

    }
}
