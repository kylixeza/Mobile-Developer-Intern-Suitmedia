package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.first

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.kylix.mobiledeveloperintern2022suitmedia.R
import com.kylix.mobiledeveloperintern2022suitmedia.base.BaseActivity
import com.kylix.mobiledeveloperintern2022suitmedia.databinding.ActivityFirstScreenBinding
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.common.buildAestheticDialog
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.second.SecondScreenActivity
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.validator.ConstraintValidator
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.validator.FirstScreenValidator
import com.kylix.mobiledeveloperintern2022suitmedia.util.Constant.EXTRA_NAME
import com.kylix.mobiledeveloperintern2022suitmedia.util.ScreenOrientation
import com.thecode.aestheticdialogs.DialogType
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstScreenActivity : BaseActivity<ActivityFirstScreenBinding>() {
    
    private val viewModel by viewModel<FirstScreenViewModel>()
    
    override fun inflateViewBinding(): ActivityFirstScreenBinding = ActivityFirstScreenBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun onCreateBehaviour(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
    
    override fun ActivityFirstScreenBinding.binder() {
        btnCheck.setOnClickListener {
            val palindromeText = edtPalindrome.text.toString()
            viewModel.isPalindrome(palindromeText)
            lifecycleScope.launchWhenStarted {
                viewModel.isPalindrome.collect { isPalindrome ->
                    if(isPalindrome)
                        buildAestheticDialog(
                            DialogType.SUCCESS,
                            "Palindrome Check",
                            resources.getString(R.string.palindrome_true)
                        ) {
                            it.dismiss()
                        }
            
                    else
                        buildAestheticDialog(
                            DialogType.ERROR,
                            "Palindrome Check",
                            resources.getString(R.string.palindrome_false)
                        ) {
                            it.dismiss()
                        }
                }
            }
        }
        
        btnNext.setOnClickListener {
            val name = edtName.text.toString()
            val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            startActivity(intent)
        }
    }
    
    override fun validateConstraint(): ConstraintValidator {
        return FirstScreenValidator(binding)
    }
}