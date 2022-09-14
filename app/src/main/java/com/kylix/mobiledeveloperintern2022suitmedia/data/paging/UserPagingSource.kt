package com.kylix.mobiledeveloperintern2022suitmedia.data.paging

import  androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kylix.mobiledeveloperintern2022suitmedia.data.source.remote.api.ApiService
import com.kylix.mobiledeveloperintern2022suitmedia.data.source.remote.response.UserResponse

class UserPagingSource(
    private val apiService: ApiService,
): PagingSource<Int, UserResponse>() {
    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition
    }
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val page = params.key ?: 1
            val response = apiService.fetchUsers(page)
            var nextPage: Int? = null
            if (response.totalPages <= 2) {
                nextPage = page + 1
            }
            LoadResult.Page(
                data = response.data,
                prevKey = null, nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    
}