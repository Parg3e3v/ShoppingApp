package com.parg3v.domain.module

import com.parg3v.domain.repository.ProductsRepository
import com.parg3v.domain.use_cases.GetAllCategoriesUseCase
import com.parg3v.domain.use_cases.GetHighlyRatedProductsUseCase
import com.parg3v.domain.use_cases.GetProductByIdUseCase
import com.parg3v.domain.use_cases.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Provides
    @Singleton
    fun provideGetAllCategoriesUseCase(productsRepository: ProductsRepository): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetHighlyRatedProductsUseCase(productsRepository: ProductsRepository): GetHighlyRatedProductsUseCase {
        return GetHighlyRatedProductsUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsByCategoryUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductByIdUseCase(productsRepository: ProductsRepository): GetProductByIdUseCase {
        return GetProductByIdUseCase(productsRepository)
    }
}