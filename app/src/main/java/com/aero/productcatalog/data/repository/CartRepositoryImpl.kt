package com.aero.productcatalog.data.repository

import com.aero.productcatalog.domain.model.Cart
import com.aero.productcatalog.domain.model.Product
import com.aero.productcatalog.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor() : CartRepository {
    private val _cart = MutableStateFlow(Cart())
    override val cart: StateFlow<Cart> = _cart.asStateFlow()

    override fun addToCart(product: Product, quantity: Int) {
        _cart.value = _cart.value.addProduct(product, quantity)
    }

    override fun removeFromCart(productId: Int) {
        _cart.value = _cart.value.removeProduct(productId)
    }

    override fun updateQuantity(productId: Int, quantity: Int) {
        _cart.value = _cart.value.updateQuantity(productId, quantity)
    }

    override fun clearCart() {
        _cart.value = _cart.value.clear()
    }

    override fun getCart(): Cart {
        return _cart.value
    }
}
