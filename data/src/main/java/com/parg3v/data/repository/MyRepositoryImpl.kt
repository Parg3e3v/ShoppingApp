package com.parg3v.data.repository

import com.parg3v.data.remote.MyApi
import com.parg3v.domain.repository.MyRepository

class MyRepositoryImpl(
    private val api: MyApi
): MyRepository {
    override suspend fun doNetworkCall() {

    }
}