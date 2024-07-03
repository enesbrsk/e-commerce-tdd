package com.demo.cart.application.command.model.request

data class RemoveItemFromCartRequest(
    val command: String,
    val payload: Payload
) {
    data class Payload(
        val itemId: Long
    )
}

