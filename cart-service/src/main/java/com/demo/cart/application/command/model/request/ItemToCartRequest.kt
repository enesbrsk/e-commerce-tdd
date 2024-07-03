package com.demo.cart.application.command.model.request

import com.demo.cart.domain.enums.ItemType

data class ItemToCartRequest @JvmOverloads constructor(

    val command: ItemType,
    val payload: ItemToCartRequestPayload

){
    data class ItemToCartRequestPayload(
        val itemId: Long,
        val categoryId: Int,
        val sellerId: Int,
        val price: Double,
        val quantity: Int
    )

    companion object {
/*
        @JvmStatic
        fun convertToCart(from: CartRequest): Cart {
            return Cart()
        }*/

    }

}