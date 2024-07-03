package com.demo.cart.application.command.controller;

import com.demo.cart.application.command.model.request.RemoveItemFromCartRequest;
import com.demo.cart.application.command.model.request.ItemToCartRequest;
import com.demo.cart.application.command.model.request.VasItemToCartRequest;
import com.demo.cart.application.command.model.response.ResponseMessage;
import com.demo.cart.application.command.service.CartCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartCommandController {

    private final CartCommandService cartCommandService;

    public CartCommandController(CartCommandService cartCommandService) {
        this.cartCommandService = cartCommandService;
    }


    @PostMapping("/item")
    public ResponseEntity<ResponseMessage> addItemToCart(@RequestBody ItemToCartRequest itemToCartRequest) {
        var result = cartCommandService.createItemToCart(itemToCartRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/vasItem")
    public ResponseEntity<ResponseMessage> addItemToCart(@RequestBody VasItemToCartRequest vasItemToCartRequest) {
        var result = cartCommandService.createVasItemToCart(vasItemToCartRequest);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/item")
    public ResponseEntity<ResponseMessage> removeItemFromCart(@RequestBody RemoveItemFromCartRequest removeItemFromCartRequest) {
        var result = cartCommandService.removeItemFromCart(removeItemFromCartRequest);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/resetCart")
    public ResponseEntity<ResponseMessage> removeAllItemFromCart() {
        var result = cartCommandService.removeAllItemFromCart();
        return ResponseEntity.ok(result);
    }

}
