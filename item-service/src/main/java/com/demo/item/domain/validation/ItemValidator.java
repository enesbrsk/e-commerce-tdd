package com.demo.item.domain.validation;

import com.demo.item.application.command.model.dto.ItemDto;
import com.demo.item.domain.constant.Constants;

import java.util.List;

public class ItemValidator {

    public static boolean validaItem(ItemDto itemDto, List<Long> cartItemIdList){

        if (!cartItemIdList.contains(itemDto.getItemId()) && cartItemIdList.size() == Constants.MAX_UNIQUE_ITEMS){
            throw new IllegalArgumentException("Sepete 10 benzersiz öğe içerebilir.");
        }

        return true;

    }
}
