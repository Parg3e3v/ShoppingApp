package com.parg3v.data.di.modules

import com.parg3v.data.remote.MyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): MyApi{
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .build()
            .create(MyApi::class.java)
    }
}