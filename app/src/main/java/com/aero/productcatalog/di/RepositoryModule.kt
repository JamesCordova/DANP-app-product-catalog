package com.aero.productcatalog.di

import com.aero.productcatalog.data.remote.CategoryRemoteDataSource
import com.aero.productcatalog.data.remote.CategoryRemoteDataSourceImpl
import com.aero.productcatalog.data.remote.ProductRemoteDataSource
import com.aero.productcatalog.data.remote.ProductRemoteDataSourceImpl
import com.aero.productcatalog.data.repository.CartRepositoryImpl
import com.aero.productcatalog.data.repository.ProductRepositoryImpl
import com.aero.productcatalog.data.repository.ThemeRepositoryImpl
import com.aero.productcatalog.domain.repository.CartRepository
import com.aero.productcatalog.domain.repository.ProductRepository
import com.aero.productcatalog.domain.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository

    @Binds
    @Singleton
    abstract fun bindProductRemoteDataSource(
        productRemoteDataSourceImpl: ProductRemoteDataSourceImpl
    ): ProductRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCategoryRemoteDataSource(
        categoryRemoteDataSourceImpl: CategoryRemoteDataSourceImpl
    ): CategoryRemoteDataSource
}
