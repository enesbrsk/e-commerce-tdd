package com.demo.cart.domain.repository;

import com.demo.cart.domain.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findFirstByOrderByIdAsc();

    Optional<Cart> findById(String id);

}
