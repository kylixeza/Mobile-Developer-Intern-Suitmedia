package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.first

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirstScreenViewModel: ViewModel() {
    
    private val _isPalindrome = MutableStateFlow(false)
    val isPalindrome = _isPalindrome.asStateFlow()
    
    fun isPalindrome(input: String) {
        val inputReversed = input.reversed()
        _isPalindrome.value = input.equals(inputReversed, ignoreCase = true)
    }
    
}