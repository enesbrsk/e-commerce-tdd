package com.demo.item.application.command.service.implemantation;

import com.demo.item.application.command.model.dto.VasItemDto;
import com.demo.item.application.command.service.ItemService;
import com.demo.item.application.query.service.ItemServiceQuery;
import com.demo.item.domain.entities.Item;
import com.demo.item.domain.enums.CategoryType;
import com.demo.item.domain.enums.SellerType;
import com.demo.item.domain.exception.ItemNotFoundException;
import com.demo.item.domain.repository.VasItemRepository;
import com.demo.item.infrastructure.message.ItemMessageSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VasItemServiceImpl {

    private final VasItemRepository vasItemRepository;
    private final DefaultItemServiceImpl defaultItemServiceImpl;

    public VasItemServiceImpl(VasItemRepository vasItemRepository, DefaultItemServiceImpl defaultItemServiceImpl) {
        this.vasItemRepository = vasItemRepository;
        this.defaultItemServiceImpl = defaultItemServiceImpl;
    }

    public void addVasItemToCart(VasItemDto vasItemDto) {

        Item item = defaultItemServiceImpl.getItemByItemId(vasItemDto.getItemId());

        if (vasItemDto.getVasSellerId() != SellerType.VAS_SELLER_ID.value) {
            throw new ItemNotFoundException("VasItemId should be 5003 ");
        }

        if(item.getCategoryId() != CategoryType.DIGITAL_ITEM.value && item.getPrice() < vasItemDto.getPrice()){
            throw new ItemNotFoundException("Vas Item is not bigger than Default Item price");
        }

        if ((item.getCategoryId() == CategoryType.DEFAULT_ITEM_FURNITURE.value || item.getCategoryId() == CategoryType.DEFAULT_ITEM_ELECTRONICS.value)) {
            Integer countItem = vasItemRepository.findBySameDefaultItem(item.getCategoryId(), item.getCategoryId() == CategoryType.DEFAULT_ITEM_FURNITURE.value ? CategoryType.DEFAULT_ITEM_ELECTRONICS.value : CategoryType.DEFAULT_ITEM_FURNITURE.value);
            if (!(countItem < 3)) {
                throw new ItemNotFoundException("Maximum limit of VasItems reached for this DefaultItem.");
            }
        }
        vasItemRepository.save(VasItemDto.convertToVasItem(vasItemDto, item));

        defaultItemServiceImpl.itemToPromotion(item.getCartId());
    }

}
