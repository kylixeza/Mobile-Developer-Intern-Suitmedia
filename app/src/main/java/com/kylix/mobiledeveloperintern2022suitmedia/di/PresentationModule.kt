package com.kylix.mobiledeveloperintern2022suitmedia.di

import com.kylix.mobiledeveloperintern2022suitmedia.adapter.UserPagingAdapter
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.first.FirstScreenViewModel
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.second.SecondScreenViewModel
import com.kylix.mobiledeveloperintern2022suitmedia.presentation.ui.third.ThirdScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FirstScreenViewModel() }
    viewModel { SecondScreenViewModel() }
    viewModel { ThirdScreenViewModel(get()) }
}

val adapterModule = module {
    factory { UserPagingAdapter() }
}