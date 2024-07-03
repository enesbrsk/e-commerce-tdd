package com.demo.cart;

import com.demo.cart.domain.entities.Cart;
import com.demo.cart.domain.repository.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLinerImpl implements CommandLineRunner {

    private final CartRepository cartRepository;

    public CommandLinerImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Cart cart = new Cart();
        Cart cartId = cartRepository.save(cart);
        System.out.println(cartId);
    }
}
