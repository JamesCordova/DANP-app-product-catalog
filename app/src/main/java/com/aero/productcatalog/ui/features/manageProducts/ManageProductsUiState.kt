package com.aero.productcatalog.ui.features.manageProducts

import com.aero.productcatalog.domain.model.Product

data class ManageProductsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
