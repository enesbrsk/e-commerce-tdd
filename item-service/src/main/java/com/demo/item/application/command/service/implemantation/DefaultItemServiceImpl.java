package com.demo.item.application.command.service.implemantation;

import com.demo.item.application.command.model.dto.ItemDto;
import com.demo.item.application.command.service.ItemService;
import com.demo.item.application.query.service.ItemServiceQuery;
import com.demo.item.domain.entities.Item;
import com.demo.item.domain.repository.ItemRepository;
import com.demo.item.domain.validation.ItemValidator;
import com.demo.item.infrastructure.message.ItemMessageSender;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DefaultItemServiceImpl extends ItemService {

    private final ItemRepository itemRepository;
    private final ItemMessageSender itemMessageSender;
    private final ItemServiceQuery itemServiceQuery;

    public DefaultItemServiceImpl(ItemRepository itemRepository, ItemMessageSender itemMessageSender, ItemServiceQuery itemServiceQuery) {
        this.itemRepository = itemRepository;
        this.itemMessageSender = itemMessageSender;
        this.itemServiceQuery = itemServiceQuery;
    }

    @Override
    public void addItemToCart(ItemDto itemDto) {

        validateItem(itemDto);
        itemRepository.save(ItemDto.convertToItem(itemDto));

        itemToPromotion(itemDto.getCartId());
        System.out.println(String.format("Default Received Message: [%s] ", itemDto.getCommand()));

    }

    protected boolean validateItem(ItemDto itemDto) {
        List<Long> itemList = itemRepository.findAllByItemId().stream().distinct().toList();
        return ItemValidator.validaItem(itemDto, itemList);
    }

    protected Item getItemByItemId(Long itemId) {
        return itemRepository.findByItemId(itemId);
    }

    protected void itemToPromotion(String cartId){
        List<Item> itemList = itemServiceQuery.getAllItemByCartId(cartId);
        itemMessageSender.sendItemToPromotion("added-item-to-cart",itemList);
    }

    @Transactional
    public void removeItemFromCartByItemId(Long itemId) {
        Item item = getItemByItemId(itemId);

        List<Item> itemList = itemServiceQuery.getAllItemByCartId(item.getCartId());
        itemRepository.deleteById(itemId);

        List<Item> filteredList = itemList.stream()
                .filter(data -> !Objects.equals(data.getItemId(), itemId))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()){
            filteredList.add(new Item(0L, new ArrayList<>(), item.getCartId(), 0,0,0,0));
        }

        itemMessageSender.sendItemToPromotion("added-item-to-cart",filteredList);
    }

    @Transactional
    public void removeAllItemFromCart() {
        // Böyle yapılmasını sebebi demo olduğu için cartId
        // proje ayağa kaldırılırken oluşturuluyor ve userId ya da cartId bilgisi tutulmadığından bir şekilde alınmaya çalışılmaktadır.
        List<Item> data = itemRepository.findAll();
        String cartId = data.get(0).getCartId();

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(0L, new ArrayList<>(), cartId, 0,0,0,0));

        itemRepository.deleteByCartId(cartId);

        itemMessageSender.sendItemToPromotion("added-item-to-cart",itemList);
    }
}
