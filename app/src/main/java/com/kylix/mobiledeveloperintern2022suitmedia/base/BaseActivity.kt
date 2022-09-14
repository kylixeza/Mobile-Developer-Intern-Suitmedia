package com.kylix.mobiledeveloperintern2022suitmedia.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.validator.ConstraintValidator
import com.kylix.mobiledeveloperintern2022suitmedia.util.ScreenOrientation

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    private lateinit var _binding: VB
    val binding get() = _binding

    abstract fun inflateViewBinding(): VB
    abstract fun determineScreenOrientation(): ScreenOrientation?
    abstract fun VB.binder()
    
    open fun onCreateBehaviour(savedInstanceState: Bundle?) {}
    open fun validateConstraint(): ConstraintValidator? { return null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateViewBinding()
        setContentView(binding.root)

        onCreateBehaviour(savedInstanceState)
        
        val screenOrientation = determineScreenOrientation()

        requestedOrientation = if(screenOrientation != null) {
            if (screenOrientation == ScreenOrientation.PORTRAIT)
                android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            else
                android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        binding.apply {
            binder()
            validateConstraint()?.validate()
        }

    }

}