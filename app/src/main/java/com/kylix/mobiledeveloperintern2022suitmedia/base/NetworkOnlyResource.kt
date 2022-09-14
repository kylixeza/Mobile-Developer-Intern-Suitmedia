package com.kylix.mobiledeveloperintern2022suitmedia.base

import android.util.Log
import com.kylix.mobiledeveloperintern2022suitmedia.data.util.RemoteResponse
import com.kylix.mobiledeveloperintern2022suitmedia.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is RemoteResponse.Success<RequestType> -> {
                Log.d("Network only resource", apiResponse.data.toString())
                emit(Resource.Success(mapTransform(apiResponse.data)))
            }
            is RemoteResponse.Empty -> emit(Resource.Empty())
            is RemoteResponse.Error -> emit(Resource.Error<ResultType>(apiResponse.errorMessage))
        }
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun createCall(): Flow<RemoteResponse<RequestType>>

    protected abstract fun mapTransform(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}