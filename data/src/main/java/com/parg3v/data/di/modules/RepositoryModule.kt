package com.parg3v.data.di.modules

import com.parg3v.data.repository.MyRepositoryImpl
import com.parg3v.domain.repository.MyRepository
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
    abstract fun bindMyRepository(
        myRepository: MyRepositoryImpl
    ): MyRepository
}