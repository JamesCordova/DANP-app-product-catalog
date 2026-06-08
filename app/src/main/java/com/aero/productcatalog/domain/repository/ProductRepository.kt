package com.aero.productcatalog.domain.repository

import com.aero.productcatalog.domain.model.Product
import com.aero.productcatalog.domain.model.ProductCategory
import kotlinx.coroutines.flow.StateFlow

interface ProductRepository {
    val products: StateFlow<List<Product>>
    val categories: StateFlow<List<ProductCategory>>
    val favoriteProductIds: StateFlow<Set<Int>>
    fun toggleFavorite(productId: Int)
    suspend fun addCategory(name: String, description: String?)
    suspend fun addProduct(name: String, description: String, price: Double, categoryId: Int, imageUrl: String)
    suspend fun updateProduct(productId: Int, name: String, description: String, price: Double, categoryId: Int, imageUrl: String)
    suspend fun deleteProduct(productId: Int)
}
