package com.example.mvrxexample

import androidx.multidex.MultiDexApplication
import com.example.mvrxexample.di.currencyModule
import com.example.mvrxexample.di.homeModule
import com.example.mvrxexample.di.mainModule
import com.example.mvrxexample.di.mapperModule
import com.example.mvrxexample.di.network.apiServiceModule
import com.example.mvrxexample.di.network.retrofitModule
import com.example.mvrxexample.di.repositoryModule
import com.example.mvrxexample.di.settingsModule
import com.example.mvrxexample.di.utils.gsonModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MvRxApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MvRxApp)
            modules(
                listOf(
                    mainModule,
                    repositoryModule,
                    settingsModule,
                    homeModule,
                    apiServiceModule,
                    retrofitModule,
                    mapperModule,
                    currencyModule,
                    gsonModule
                )
            )
        }
    }
}