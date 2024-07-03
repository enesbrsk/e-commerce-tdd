package com.demo.item.domain.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class VasItem @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val vasItemId: Long,
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    val item: Item,
    val vasCategoryId: Int,
    val vasSellerId: Int,
    val price: Double,
    val quantity: Int,

    ){
}

