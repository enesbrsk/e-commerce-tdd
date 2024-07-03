package com.demo.cart.application.command.model.request

import com.demo.cart.domain.enums.ItemType

data class VasItemToCartRequest @JvmOverloads constructor(

    val command: ItemType,
    val payload: VasItemToCartRequestPayload

){
    data class VasItemToCartRequestPayload(
        val itemId: Long,
        val vasItemId: Long,
        val vasCategoryId: Int,
        val vasSellerId: Int,
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
