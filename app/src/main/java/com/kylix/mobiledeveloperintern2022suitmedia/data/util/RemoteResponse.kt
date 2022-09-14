package com.kylix.mobiledeveloperintern2022suitmedia.data.util

sealed class RemoteResponse<out T> {
    data class Success<out T> (val data: T): RemoteResponse<T>()
    data class Error(val errorMessage: String): RemoteResponse<Nothing>()
    data class Empty(val message: String = ""): RemoteResponse<Nothing>()
}