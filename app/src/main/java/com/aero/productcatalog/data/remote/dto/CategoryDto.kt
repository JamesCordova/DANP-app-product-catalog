package com.aero.productcatalog.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int,
    @SerialName("created_at")
    val createdAt: String? = null,
    val name: String,
    val description: String? = null
)

@Serializable
data class CategoryInsertDto(
    val name: String,
    val description: String? = null
)
