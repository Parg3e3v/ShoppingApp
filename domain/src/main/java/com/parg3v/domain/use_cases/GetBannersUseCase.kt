package com.parg3v.domain.use_cases

import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.repository.BannersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
    private val bannersRepository: BannersRepository
) {
    operator fun invoke(): Flow<ResultOf<List<String>>> = flow{
        try {
            emit(ResultOf.Loading())
            val banners = bannersRepository.getBanners()
            emit(ResultOf.Success(banners))
        }catch (e: IOException){
            emit(ResultOf.Failure("Something went wrong. Check your internet connection"))
        }
    }
}