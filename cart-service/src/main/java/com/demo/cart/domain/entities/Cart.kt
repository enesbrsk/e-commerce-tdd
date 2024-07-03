package com.demo.cart.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal

@Entity
data class Cart @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val totalAmount: BigDecimal,
    val totalDiscount: BigDecimal,
    val totalQuantity: Double,

    ){
}


