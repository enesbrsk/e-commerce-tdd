package com.demo.promotion.application.command.model.dto

import java.math.BigDecimal

data class CartDto @JvmOverloads constructor(

    val cartId: String,
    val totalAmount: BigDecimal,
    val totalDiscount: BigDecimal,
    val totalQuantity: Double,

    ){

}

