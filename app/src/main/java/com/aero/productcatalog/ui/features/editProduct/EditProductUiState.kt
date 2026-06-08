package com.aero.productcatalog.ui.features.editProduct

import com.aero.productcatalog.domain.model.ProductCategory

data class EditProductUiState(
    val productId: Int = 0,
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val categories: List<ProductCategory> = emptyList(),
    val selectedCategory: ProductCategory? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)
