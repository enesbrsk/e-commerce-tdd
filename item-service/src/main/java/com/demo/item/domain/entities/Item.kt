package com.demo.item.domain.entities

import jakarta.persistence.*

@Entity
data class Item @JvmOverloads constructor(

    @Id
    val itemId: Long? = null,

    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER,  cascade = [CascadeType.ALL],orphanRemoval = true)
    val vasItem: List<VasItem>? = ArrayList(),
    val cartId: String,
    val categoryId: Int,
    val sellerId: Int,
    val price: Double,
    val quantity: Int,

    ){
}

