package com.parg3v.domain.module

import com.parg3v.domain.repository.BannersRepository
import com.parg3v.domain.use_cases.GetBannersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BannersModule {
    @Provides
    @Singleton
    fun provideGetBannersUseCase(bannersRepository: BannersRepository): GetBannersUseCase {
        return GetBannersUseCase(bannersRepository)
    }
}