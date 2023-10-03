package com.parg3v.domain.repository

interface BannersRepository {

    suspend fun getBanners(): List<String>
}