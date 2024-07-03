package com.demo.cart.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String s){
        super(s);
    }
}
