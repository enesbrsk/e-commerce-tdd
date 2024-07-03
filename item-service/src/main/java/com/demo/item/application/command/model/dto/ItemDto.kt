package com.demo.item.application.command.model.dto

import com.demo.item.domain.entities.Item
import com.demo.item.domain.entities.VasItem
import com.demo.item.domain.enums.ItemType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.cache.spi.support.AbstractReadWriteAccess

data class ItemDto @JvmOverloads constructor(

    val command: ItemType,
    val cartId: String,
    val itemId: Long?,
    val categoryId: Int,
    val sellerId: Int,
    val price: Double,
    val quantity: Int

){
    companion object {
        // JSON'dan ItemDto'ya dönüşüm işlemi
        @JvmStatic
        @JsonCreator
        fun fromJson(
            @JsonProperty("command") command: ItemType,
            @JsonProperty("cartId") cartId: String,
            @JsonProperty("itemId") itemId: Long?,
            @JsonProperty("categoryId") categoryId: Int,
            @JsonProperty("sellerId") sellerId: Int,
            @JsonProperty("price") price: Double,
            @JsonProperty("quantity") quantity: Int
        ): ItemDto {
            return ItemDto(command, cartId, itemId, categoryId, sellerId, price,quantity)
        }

        @JvmStatic
        fun convertToItem(from: ItemDto): Item {
            return Item(from.itemId,null,from.cartId,from.categoryId,from.sellerId,from.price,from.quantity)
        }

    }

}
