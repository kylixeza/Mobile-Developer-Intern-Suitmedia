package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.kylix.mobiledeveloperintern2022suitmedia.data.paging.UserPagingSource
import com.kylix.mobiledeveloperintern2022suitmedia.model.User
import com.kylix.mobiledeveloperintern2022suitmedia.util.State
import com.kylix.mobiledeveloperintern2022suitmedia.util.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ThirdScreenViewModel(
    private val pagingSource: UserPagingSource
): ViewModel() {
    
    private val _state = MutableStateFlow(State.LOADING)
    val state = _state.asStateFlow()
    
    private val _user = MutableStateFlow<PagingData<User>?>(null)
    val user = _user.asStateFlow()
    
    suspend fun getListData() = run {
        _state.value = State.LOADING
        Pager(
            config = PagingConfig(pageSize = 6, maxSize = 18),
            pagingSourceFactory = { pagingSource })
            .flow
            .map { it.map { it.toUser() } }
            .cachedIn(viewModelScope)
            .catch {
                _state.value = State.ERROR
            }
            .collect {
                _user.value = it
                _state.value = State.SUCCESS
            }
    }
}
