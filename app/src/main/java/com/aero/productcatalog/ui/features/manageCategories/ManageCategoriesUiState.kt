package com.aero.productcatalog.ui.features.manageCategories

import com.aero.productcatalog.domain.model.ProductCategory

data class ManageCategoriesUiState(
    val categories: List<ProductCategory> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
