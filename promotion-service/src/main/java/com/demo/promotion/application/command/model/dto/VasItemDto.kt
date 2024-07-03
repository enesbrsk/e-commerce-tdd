package com.demo.promotion.application.command.model.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

data class VasItemDto @JvmOverloads constructor(

    val id: Long? = null,
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
            @JsonProperty("id") id: Long?,
            @JsonProperty("vasItemId") vasItemId: Long,
            @JsonProperty("vasCategoryId") vasCategoryId: Int,
            @JsonProperty("vasSellerId") vasSellerId: Int,
            @JsonProperty("price") price: Double,
            @JsonProperty("quantity") quantity: Int
        ): VasItemDto {
            return VasItemDto(id, vasItemId, vasCategoryId, vasSellerId, price,quantity)
        }

    }

}
