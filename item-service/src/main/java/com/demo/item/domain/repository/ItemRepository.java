package com.demo.item.domain.repository;

import com.demo.item.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i.itemId FROM Item as i")
    List<Long> findAllByItemId();
    Item findByItemId(Long itemId);

    @Query("SELECT i.categoryId FROM Item as i where i.categoryId = :categoryId")
    List<Long> findAllByCategoryId(@Param("categoryId") Integer categoryId);

    List<Item> findByCartId(String cartId);
    boolean deleteByItemId(Long itemId);

    void deleteByCartId(String cartId);

}
