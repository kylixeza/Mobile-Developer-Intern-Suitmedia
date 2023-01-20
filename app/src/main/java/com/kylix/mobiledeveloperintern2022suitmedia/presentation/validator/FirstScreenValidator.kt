package com.kylix.mobiledeveloperintern2022suitmedia.presentation.validator

import android.annotation.SuppressLint
import com.jakewharton.rxbinding2.widget.RxTextView
import com.kylix.mobiledeveloperintern2022suitmedia.databinding.ActivityFirstScreenBinding

class FirstScreenValidator(
    private val binding: ActivityFirstScreenBinding
): ConstraintValidator {
    @SuppressLint("CheckResult")
    override fun validate() {
        val emptyName = RxTextView.textChanges(binding.edtName)
            .map { it.toString() }
            .map { return@map it.isEmpty() }
            .distinctUntilChanged()
            
        emptyName.subscribe {
            binding.btnNext.isEnabled = !it
        }
        
        val emptyPalindrome = RxTextView.textChanges(binding.edtPalindrome)
            .map { it.toString() }
            .map { return@map it.isEmpty() }
            .distinctUntilChanged()
        
        emptyPalindrome.subscribe {
            binding.btnCheck.isEnabled = !it
        }
    }
}