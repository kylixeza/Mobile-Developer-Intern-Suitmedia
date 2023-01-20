package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.second

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.kylix.mobiledeveloperintern2022suitmedia.R
import com.kylix.mobiledeveloperintern2022suitmedia.base.BaseActivity
import com.kylix.mobiledeveloperintern2022suitmedia.databinding.ActivitySecondScreenBinding
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.third.ThirdScreenActivity
import com.kylix.mobiledeveloperintern2022suitmedia.util.Constant.EXTRA_NAME
import com.kylix.mobiledeveloperintern2022suitmedia.util.Constant.USER_NAME
import com.kylix.mobiledeveloperintern2022suitmedia.util.ScreenOrientation
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondScreenActivity : BaseActivity<ActivitySecondScreenBinding>() {
    
    private val viewModel by viewModel<SecondScreenViewModel>()
    
    override fun inflateViewBinding(): ActivitySecondScreenBinding = ActivitySecondScreenBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun onCreateBehaviour(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.white)
        }
    }
    
    override fun ActivitySecondScreenBinding.binder() {
        val nameArg = intent.getStringExtra(EXTRA_NAME) ?: ""
        
        secondScreenAppBar.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.hide()
            ivBack.setOnClickListener { finish() }
            tvTitle.text = resources.getString(R.string.second_screen)
        }
        
        viewModel.setName(nameArg)
        
        lifecycleScope.launchWhenStarted {
            viewModel.name.collect {
                tvName.text = it
            }
        }
    
        tvSelectedUser.text = USER_NAME ?: ""
        
        btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onResume() {
        super.onResume()
        binding.tvSelectedUser.text = USER_NAME ?: ""
    }
    
}