package com.demo.cart.repository;

import com.demo.cart.domain.entities.Cart;
import com.demo.cart.domain.enums.ErrorCode;
import com.demo.cart.domain.exception.GenericException;
import com.demo.cart.domain.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CartRepositoryTest extends BaseRepositoryTest{

    @Autowired
    private CartRepository cartRepository;


    @Test
    public void givenCartObject_whenSave_thenReturnSavedCart() {

        // given - precondition or setup
        Cart cart = new Cart("e58ed763-928c-4155-bee9-fdbaaadc15f3", BigDecimal.valueOf(100),BigDecimal.valueOf(20),10);

        // when -  action or the behaviour that we are going test
        Cart savedCart = cartRepository.save(cart);

        // then - verify the output
        assertThat(savedCart.getId()).isGreaterThan(String.valueOf(0));
        assertThat(savedCart).isNotNull();

    }

    @Test
    public void givenCartObject_whenFindById_thenCartBookObject() {

        // given - precondition or setup
        Cart cart = new Cart("e58ed763-928c-4155-bee9-fdbaaadc15f3", BigDecimal.valueOf(100),BigDecimal.valueOf(20),10);

        // when -  action or the behaviour that we are going test
        Cart savedCart = cartRepository.save(cart);

        // when -  action or the behaviour that we are going test
        Cart returnedCart = cartRepository.findById(savedCart.getId()).orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.CART_INVALID).build());

        // then - verify the output
        assertThat(returnedCart).isNotNull();
    }

}
