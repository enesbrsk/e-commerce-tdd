package com.demo.item.application.command.model.dto

import com.demo.promotion.application.command.model.dto.VasItemDto
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class ItemDto @JvmOverloads constructor(

    val cartId: String,
    val itemId: Long?,
    val vasItem: List<VasItemDto>?,
    val categoryId: Int,
    val sellerId: Int,
    val price: BigDecimal,
    val quantity: Int

) {
    companion object {
        // JSON'dan ItemDto'ya dönüşüm işlemi
        @JvmStatic
        @JsonCreator
        fun fromJson(
            @JsonProperty("cartId") cartId: String,
            @JsonProperty("itemId") itemId: Long?,
            @JsonProperty("vasItem") vasItem: List<VasItemDto>?,
            @JsonProperty("categoryId") categoryId: Int,
            @JsonProperty("sellerId") sellerId: Int,
            @JsonProperty("price") price: BigDecimal,
            @JsonProperty("quantity") quantity: Int
        ): ItemDto {
            return ItemDto(cartId, itemId, vasItem, categoryId, sellerId, price, quantity)
        }
    }
}
