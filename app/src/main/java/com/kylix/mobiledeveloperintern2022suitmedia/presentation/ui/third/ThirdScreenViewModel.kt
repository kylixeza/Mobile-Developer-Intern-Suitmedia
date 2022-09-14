package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.kylix.mobiledeveloperintern2022suitmedia.data.paging.UserPagingSource
import com.kylix.mobiledeveloperintern2022suitmedia.util.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class ThirdScreenViewModel(
    private val pagingSource: UserPagingSource
): ViewModel() {
    
    //fun fetchUsers() = repository.fetchUsers().asLiveData(Dispatchers.IO)
    
    fun getListData() =
        Pager(
            config = PagingConfig(pageSize = 6, maxSize = 18),
            pagingSourceFactory = { pagingSource })
            .flow
            .map { it.map { it.toUser() } }
            .cachedIn(viewModelScope)
            .asLiveData(Dispatchers.IO)
}
