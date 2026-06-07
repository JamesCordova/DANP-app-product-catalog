package com.aero.productcatalog.ui.features.favorites

import com.aero.productcatalog.domain.model.Product

data class FavoritesUiState(
    val favoriteProducts: List<Product> = emptyList(),
    val favoriteProductIds: Set<Int> = emptySet()
)
