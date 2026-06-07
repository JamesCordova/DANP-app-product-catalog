package com.aero.productcatalog.domain.repository

import com.aero.productcatalog.domain.model.Cart
import com.aero.productcatalog.domain.model.Product
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    val cart: StateFlow<Cart>
    fun addToCart(product: Product, quantity: Int = 1)
    fun removeFromCart(productId: Int)
    fun updateQuantity(productId: Int, quantity: Int)
    fun clearCart()
    fun getCart(): Cart
}
