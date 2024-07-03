package com.demo.cart.application.command.model

import com.demo.cart.application.command.model.request.ItemToCartRequest
import com.demo.cart.application.command.model.request.VasItemToCartRequest
import com.demo.cart.domain.enums.ItemType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

data class VasItemDto @JvmOverloads constructor(

    val command: ItemType,
    val itemId: Long?,
    val vasItemId: Long?,
    val vasCategoryId: Int,
    val vasSellerId: Int,
    val price: Double,
    val quantity: Int,

    ){
    companion object {

        @JvmStatic
        fun convertToVasItemDto(from: VasItemToCartRequest): VasItemDto {
            return VasItemDto(from.command, from.payload.itemId,from.payload.vasItemId, from.payload.vasCategoryId, from.payload.vasSellerId, from.payload.price, from.payload.quantity)
        }

    }

}
