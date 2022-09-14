package com.kylix.mobiledeveloperintern2022suitmedia

import android.app.Application
import com.kylix.mobiledeveloperintern2022suitmedia.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    pagingModule,
                    viewModelModule,
                    adapterModule
                )
            )
        }
    }
    
}