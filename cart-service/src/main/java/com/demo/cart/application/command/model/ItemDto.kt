package com.demo.cart.application.command.model

import com.demo.cart.application.command.model.request.ItemToCartRequest
import com.demo.cart.domain.enums.ItemType
import org.hibernate.cache.spi.support.AbstractReadWriteAccess

data class ItemDto @JvmOverloads constructor(

    val command: ItemType,
    val cartId: String,
    val itemId: Long?,
    val categoryId: Int,
    val sellerId: Int,
    val price: Double,
    val quantity: Int,

    ){
    companion object {

                @JvmStatic
                fun convertToItemDto(from: ItemToCartRequest, cartId: String): ItemDto {
                    return ItemDto(from.command, cartId,from.payload.itemId, from.payload.categoryId, from.payload.sellerId, from.payload.price, from.payload.quantity)
                }

    }

}