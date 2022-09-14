package com.kylix.mobiledeveloperintern2022suitmedia.di

import com.kylix.mobiledeveloperintern2022suitmedia.data.paging.UserPagingSource
import com.kylix.mobiledeveloperintern2022suitmedia.data.source.remote.api.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }
    
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val pagingModule = module {
    single { UserPagingSource(get()) }
}