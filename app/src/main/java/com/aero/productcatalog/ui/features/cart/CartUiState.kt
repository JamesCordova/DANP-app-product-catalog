package com.aero.productcatalog.ui.features.cart

import com.aero.productcatalog.domain.model.Cart

data class CartUiState(
    val cart: Cart = Cart()
)
