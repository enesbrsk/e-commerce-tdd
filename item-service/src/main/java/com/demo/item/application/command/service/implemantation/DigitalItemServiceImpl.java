package com.demo.item.application.command.service.implemantation;

import com.demo.item.application.command.model.dto.ItemDto;
import com.demo.item.application.command.service.ItemService;
import com.demo.item.application.query.service.ItemServiceQuery;
import com.demo.item.domain.constant.Constants;
import com.demo.item.domain.exception.ItemNotFoundException;
import com.demo.item.domain.repository.ItemRepository;
import com.demo.item.infrastructure.message.ItemMessageSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalItemServiceImpl extends ItemService {

    private final ItemRepository itemRepository;
    private final DefaultItemServiceImpl defaultItemServiceImpl;

    public DigitalItemServiceImpl(ItemRepository itemRepository, DefaultItemServiceImpl defaultItemServiceImpl) {
        this.itemRepository = itemRepository;
        this.defaultItemServiceImpl = defaultItemServiceImpl;
    }

    @Override
    public void addItemToCart(ItemDto itemDto) {
        defaultItemServiceImpl.validateItem(itemDto);
        List<Long> categoryIdList = itemRepository.findAllByCategoryId(itemDto.getCategoryId());

        Integer totalDigitalItem = categoryIdList.size() + itemDto.getQuantity();

        if(totalDigitalItem > Constants.MAX_DIGITAL_ITEM_QUANTITY){
            throw new ItemNotFoundException("Digital item can not have more than 5 items");
        }

        itemRepository.save(ItemDto.convertToItem(itemDto));

        defaultItemServiceImpl.itemToPromotion(itemDto.getCartId());

        System.out.println(String.format("Digital Received Message: [%s] ", itemDto.getCommand()));

    }


}
