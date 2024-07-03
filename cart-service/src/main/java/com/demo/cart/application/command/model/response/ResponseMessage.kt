package com.demo.cart.application.command.model.response

data class ResponseMessage(
    val result: Boolean = true,
    val message: String = "İtem Sepete Başarıyla Eklenmiştir."
)
