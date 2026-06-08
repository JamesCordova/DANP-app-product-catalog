package com.aero.productcatalog.data.repository

import android.util.Log
import com.aero.productcatalog.data.remote.CategoryRemoteDataSource
import com.aero.productcatalog.data.remote.ProductRemoteDataSource
import com.aero.productcatalog.data.remote.dto.CategoryInsertDto
import com.aero.productcatalog.data.remote.dto.ProductInsertDto
import com.aero.productcatalog.domain.model.Product
import com.aero.productcatalog.domain.model.ProductCategory
import com.aero.productcatalog.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val categoryRemoteDataSource: CategoryRemoteDataSource
) : ProductRepository {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    override val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _categories = MutableStateFlow<List<ProductCategory>>(emptyList())
    override val categories: StateFlow<List<ProductCategory>> = _categories.asStateFlow()

    private val _favoriteProductIds = MutableStateFlow<Set<Int>>(emptySet())
    override val favoriteProductIds: StateFlow<Set<Int>> = _favoriteProductIds.asStateFlow()

    init {
        Log.d("ProductRepo", "Initializing ProductRepositoryImpl")
        refreshProducts()
    }

    private fun refreshProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("ProductRepo", "Fetching products...")
                val remoteProducts = remoteDataSource.getProducts()
                Log.d("ProductRepo", "Fetched ${remoteProducts.size} products")

                Log.d("ProductRepo", "Fetching categories...")
                val remoteCategories = categoryRemoteDataSource.getCategories()
                Log.d("ProductRepo", "Fetched ${remoteCategories.size} categories")

                _categories.value = remoteCategories.map { ProductCategory(it.id, it.name) }

                val mappedProducts = remoteProducts.map { dto ->
                    val category = _categories.value.find { it.id == dto.categoryId }
                    Product(
                        id = dto.id,
                        name = dto.name,
                        description = dto.description ?: "",
                        price = dto.price,
                        category = category ?: ProductCategory(label = "Sin categoría"),
                        imageUrl = dto.imageUrl ?: ""
                    )
                }
                _products.value = mappedProducts
                Log.d("ProductRepo", "Products updated: ${mappedProducts.size}")
            } catch (e: Exception) {
                Log.e("ProductRepo", "Error refreshing products", e)
            }
        }
    }

    override fun toggleFavorite(productId: Int) {
        val current = _favoriteProductIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
        } else {
            current.add(productId)
        }
        _favoriteProductIds.value = current
    }

    override suspend fun addCategory(name: String, description: String?) {
        categoryRemoteDataSource.insertCategory(CategoryInsertDto(name, description))
        refreshProducts() // Recargar para obtener la nueva categoria
    }

    override suspend fun updateCategory(categoryId: Int, name: String, description: String?) {
        categoryRemoteDataSource.updateCategory(categoryId, CategoryInsertDto(name, description))
        refreshProducts()
    }

    override suspend fun deleteCategory(categoryId: Int) {
        categoryRemoteDataSource.deleteCategory(categoryId)
        refreshProducts()
    }

    override suspend fun addProduct(
        name: String,
        description: String,
        price: Double,
        categoryId: Int,
        imageUrl: String
    ) {
        remoteDataSource.insertProduct(
            ProductInsertDto(
                name = name,
                description = description,
                price = price,
                categoryId = categoryId,
                imageUrl = imageUrl
            )
        )
        refreshProducts()
    }

    override suspend fun updateProduct(
        productId: Int,
        name: String,
        description: String,
        price: Double,
        categoryId: Int,
        imageUrl: String
    ) {
        remoteDataSource.updateProduct(
            productId,
            ProductInsertDto(
                name = name,
                description = description,
                price = price,
                categoryId = categoryId,
                imageUrl = imageUrl
            )
        )
        refreshProducts()
    }

    override suspend fun deleteProduct(productId: Int) {
        remoteDataSource.deleteProduct(productId)
        refreshProducts()
    }
}
