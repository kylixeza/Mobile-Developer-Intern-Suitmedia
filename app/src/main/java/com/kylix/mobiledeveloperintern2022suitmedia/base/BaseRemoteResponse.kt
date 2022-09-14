package com.kylix.mobiledeveloperintern2022suitmedia.base

import android.util.Log
import com.kylix.mobiledeveloperintern2022suitmedia.data.util.RemoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRemoteResponse<RequestType> {

    private val value = flow {
        val response = call()
        val data = response.data
        Log.d("Base Remote Response", response.data.toString())
        if(data is List<*>) {
            if (data.isEmpty())
                emit(RemoteResponse.Empty())
            else
                emit(RemoteResponse.Success(data))
        } else {
            emit(RemoteResponse.Success(data))
        }
    }.catch {
        emit(RemoteResponse.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    abstract suspend fun call(): BaseResponse<RequestType>
    fun asFlow() = value
}