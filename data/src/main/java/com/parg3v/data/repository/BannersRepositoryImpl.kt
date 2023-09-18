package com.parg3v.data.repository

import com.parg3v.data.config.NetworkConfig.BANNER_VALUES_1
import com.parg3v.data.config.NetworkConfig.BANNER_VALUES_2
import com.parg3v.data.config.NetworkConfig.BANNER_VALUES_3
import com.parg3v.domain.repository.BannersRepository
import javax.inject.Inject

class BannersRepositoryImpl @Inject constructor(): BannersRepository {
    override suspend fun getBanners(): List<String> {
        return listOf(BANNER_VALUES_1, BANNER_VALUES_2, BANNER_VALUES_3)
    }
}