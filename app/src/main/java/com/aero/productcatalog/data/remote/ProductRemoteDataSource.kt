package com.aero.productcatalog.data.remote

import com.aero.productcatalog.data.remote.dto.ProductDto
import com.aero.productcatalog.data.remote.dto.ProductInsertDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

interface ProductRemoteDataSource {
    suspend fun getProducts(): List<ProductDto>
    suspend fun insertProduct(product: ProductInsertDto)
}

class ProductRemoteDataSourceImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProductRemoteDataSource {

    override suspend fun getProducts(): List<ProductDto> {
        return supabaseClient.postgrest["Products"].select().decodeList<ProductDto>()
    }

    override suspend fun insertProduct(product: ProductInsertDto) {
        supabaseClient.postgrest["Products"].insert(product)
    }
}
