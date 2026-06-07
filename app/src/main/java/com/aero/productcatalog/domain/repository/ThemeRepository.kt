package com.aero.productcatalog.domain.repository

import com.aero.productcatalog.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val themeMode: Flow<AppThemeMode>
    suspend fun setThemeMode(mode: AppThemeMode)
}
