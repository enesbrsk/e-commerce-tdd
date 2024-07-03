package com.demo.item.application.command.model.dto

import com.demo.item.domain.entities.Item
import com.demo.item.domain.entities.VasItem
import com.demo.item.domain.enums.ItemType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class VasItemDto @JvmOverloads constructor(

    val command: ItemType,
    val itemId: Long?,
    val vasItemId: Long,
    val vasCategoryId: Int,
    val vasSellerId: Int,
    val price: Double,
    val quantity: Int,

    ){
    companion object {

        @JvmStatic
        @JsonCreator
        fun fromJson(
            @JsonProperty("command") command: ItemType,
            @JsonProperty("itemId") itemId: Long?,
            @JsonProperty("vasItemId") vasItemId: Long,
            @JsonProperty("vasCategoryId") vasCategoryId: Int,
            @JsonProperty("vasSellerId") vasSellerId: Int,
            @JsonProperty("price") price: Double,
            @JsonProperty("quantity") quantity: Int
        ): VasItemDto {
            return VasItemDto(command, itemId,vasItemId,vasCategoryId,vasSellerId,price,quantity)
        }

        @JvmStatic
        fun convertToVasItem(from: VasItemDto,item: Item): VasItem {
            return VasItem(null,from.vasItemId, item, from.vasCategoryId, from.vasSellerId, from.price, from.quantity)
        }

    }

}
