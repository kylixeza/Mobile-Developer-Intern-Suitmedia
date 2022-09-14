package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.second

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SecondScreenViewModel: ViewModel() {
    
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
    
    fun setName(name: String?) {
        if(name != null)
            _name.value = name
    }
    
}