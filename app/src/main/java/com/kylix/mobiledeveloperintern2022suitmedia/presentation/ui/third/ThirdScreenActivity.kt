package com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.third

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.mobiledeveloperintern2022suitmedia.R
import com.kylix.mobiledeveloperintern2022suitmedia.adapter.UserPagingAdapter
import com.kylix.mobiledeveloperintern2022suitmedia.base.BaseActivity
import com.kylix.mobiledeveloperintern2022suitmedia.databinding.ActivityThirdScreenBinding
import com.kylix.mobiledeveloperintern2022suitmedia.util.ScreenOrientation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThirdScreenActivity : BaseActivity<ActivityThirdScreenBinding>() {
    
    private val viewModel by viewModel<ThirdScreenViewModel>()
    private val adapter by inject<UserPagingAdapter>()
    
    override fun inflateViewBinding(): ActivityThirdScreenBinding = ActivityThirdScreenBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun onCreateBehaviour(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.white)
        }
    }
    
    override fun ActivityThirdScreenBinding.binder() {
        
        thirdScreenAppBar.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.hide()
            tvTitle.text = resources.getString(R.string.third_screen)
            ivBack.setOnClickListener { finish() }
        }
        
        rvUser.apply {
            adapter =  this@ThirdScreenActivity.adapter
            layoutManager = LinearLayoutManager(this@ThirdScreenActivity, LinearLayoutManager.VERTICAL, false)
        }
    
        viewModel.getListData().observe(this@ThirdScreenActivity) {
            lifecycleScope.launchWhenStarted { adapter.submitData(it) }
        }
        
        refresh.setOnRefreshListener {
            viewModel.getListData().observe(this@ThirdScreenActivity) {
                lifecycleScope.launchWhenStarted {
                    adapter.submitData(it)
                }
            }
            refresh.isRefreshing = false
        }
    }
    
}