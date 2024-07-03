package com.demo.cart.application.command.model

import com.demo.cart.application.command.model.request.ItemToCartRequest
import com.demo.cart.domain.entities.Cart
import com.demo.cart.domain.enums.ItemType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.cache.spi.support.AbstractReadWriteAccess
import java.math.BigDecimal

data class CartDto @JvmOverloads constructor(

    val cartId: String,
    val totalAmount: BigDecimal,
    val totalDiscount: BigDecimal,
    val totalQuantity: Double,

    ){
    companion object {
        // JSON'dan ItemDto'ya dönüşüm işlemi
        @JvmStatic
        @JsonCreator
        fun fromJson(
            @JsonProperty("cartId") cartId: String,
            @JsonProperty("totalAmount") totalAmount: BigDecimal,
            @JsonProperty("totalDiscount") totalDiscount: BigDecimal,
            @JsonProperty("totalQuantity") totalQuantity: Double,
        ): CartDto {
            return CartDto(cartId, totalAmount, totalDiscount,totalQuantity)
        }
    }

}

