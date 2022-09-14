package com.kylix.mobiledeveloperintern2022suitmedia.data.source.remote.api

import com.kylix.mobiledeveloperintern2022suitmedia.base.BaseResponse
import com.kylix.mobiledeveloperintern2022suitmedia.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    
    @GET("users")
    suspend fun fetchUsers(
        @Query("page") page: Int,
    ): BaseResponse<List<UserResponse>>
}