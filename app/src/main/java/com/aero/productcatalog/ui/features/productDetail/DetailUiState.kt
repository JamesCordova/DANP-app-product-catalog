package com.aero.productcatalog.ui.features.productDetail

import com.aero.productcatalog.domain.model.Product

data class DetailUiState(
    val product: Product? = null,
    val isFavorite: Boolean = false
)
