package com.demo.cart.application.command.service;

import com.demo.cart.application.command.model.CartDto;
import com.demo.cart.application.command.model.ItemDto;
import com.demo.cart.application.command.model.VasItemDto;
import com.demo.cart.application.command.model.request.RemoveItemFromCartRequest;
import com.demo.cart.application.command.model.request.ItemToCartRequest;
import com.demo.cart.application.command.model.request.VasItemToCartRequest;
import com.demo.cart.application.command.model.response.ResponseMessage;
import com.demo.cart.domain.entities.Cart;
import com.demo.cart.domain.enums.ErrorCode;
import com.demo.cart.domain.exception.GenericException;
import com.demo.cart.domain.repository.CartRepository;
import com.demo.cart.infrastructure.message.CartMessageSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartCommandService {

    private final CartRepository cartRepository;
    private final CartMessageSender cartMessageSender;

    public CartCommandService(CartRepository cartRepository, CartMessageSender cartMessageSender) {
        this.cartRepository = cartRepository;
        this.cartMessageSender = cartMessageSender;
    }

    public void addCart(CartDto cartDto){
        Cart cart = cartRepository.findById(cartDto.getCartId())
                .orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.CART_INVALID).build());

        BigDecimal totalAmount = cartDto.getTotalAmount().subtract(cartDto.getTotalDiscount());
        Cart newCart = new Cart(cart.getId(), totalAmount ,cartDto.getTotalDiscount(), cartDto.getTotalQuantity());
        cartRepository.save(newCart);
    }

    public ResponseMessage createItemToCart(ItemToCartRequest itemToCartRequest) {

        Optional<Cart> cart = cartRepository.findFirstByOrderByIdAsc();
        cartMessageSender.sendItem("create-cart-item",ItemDto.convertToItemDto(itemToCartRequest, Objects.requireNonNull(cart.get().getId())));

        return new ResponseMessage();

    }

    public ResponseMessage createVasItemToCart(VasItemToCartRequest vasItemToCartRequest) {
        cartMessageSender.sendVasItem("create-cart-vasItem", VasItemDto.convertToVasItemDto(vasItemToCartRequest));
        return new ResponseMessage(true,"Vas Item Başarıyla Eklenmiştir");
    }

    public ResponseMessage removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest) {
        cartMessageSender.sendDeleteItemToCart("delete-item-from-cart",removeItemFromCartRequest.getPayload().getItemId());
        return new ResponseMessage(true, String.format("%s İtem Başarıyla Silinmiştir.",removeItemFromCartRequest.getPayload().getItemId()));
    }

    public ResponseMessage removeAllItemFromCart() {
        cartMessageSender.sendRemoveAllItemFromCart("remove-all-item-from-cart");
        return new ResponseMessage(true, "Item'lar Başarıyla Kaldırılmıştır.");
    }
}
