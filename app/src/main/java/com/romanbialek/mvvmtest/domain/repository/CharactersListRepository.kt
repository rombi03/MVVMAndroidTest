package com.romanbialek.mvvmtest.domain.repository

import com.romanbialek.mvvmtest.domain.model.GetCharactersListResponse
import com.romanbialek.mvvmtest.domain.model.base.BaseResponse
import io.reactivex.Single

interface CharactersListRepository {

    fun getCharacters(offset: Int?): Single<BaseResponse<GetCharactersListResponse>>
}