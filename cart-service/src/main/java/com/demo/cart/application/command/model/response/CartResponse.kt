package com.demo.cart.application.command.model.response

data class CartResponse @JvmOverloads constructor(

    val name:String

){
    companion object{
/*
        @JvmStatic
        fun convertToCartResponse(from:Cart): CartResponse {
            return CartResponse(null)
        }*/

    }

}