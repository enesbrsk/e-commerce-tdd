package com.demo.item.domain.repository;

import com.demo.item.domain.entities.VasItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VasItemRepository extends JpaRepository<VasItem,Long> {


    VasItem findByVasItemId(Long vasItemId);
    List<VasItem> findByItemItemId(Long itemId);

    @Query("SELECT count(*) FROM VasItem where item.categoryId = :categoryId or item.categoryId = :categoryId2")
    Integer findBySameDefaultItem(@Param("categoryId") Integer categoryId,@Param("categoryId2") Integer categoryId2);


}
