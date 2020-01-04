package com.example.mvrxexample

import androidx.multidex.MultiDexApplication
import com.example.mvrxexample.di.mainModule
import com.example.mvrxexample.di.repositoryModule
import com.example.mvrxexample.di.settingsModule
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
                    settingsModule
                )
            )
        }
    }
}