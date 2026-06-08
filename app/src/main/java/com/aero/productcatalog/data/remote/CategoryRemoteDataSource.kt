package com.aero.productcatalog.data.remote

import com.aero.productcatalog.data.remote.dto.CategoryDto
import com.aero.productcatalog.data.remote.dto.CategoryInsertDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

interface CategoryRemoteDataSource {
    suspend fun getCategories(): List<CategoryDto>
    suspend fun insertCategory(category: CategoryInsertDto)
}

class CategoryRemoteDataSourceImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : CategoryRemoteDataSource {

    override suspend fun getCategories(): List<CategoryDto> {
        return supabaseClient.postgrest["ProductCategories"].select().decodeList<CategoryDto>()
    }

    override suspend fun insertCategory(category: CategoryInsertDto) {
        supabaseClient.postgrest["ProductCategories"].insert(category)
    }
}
