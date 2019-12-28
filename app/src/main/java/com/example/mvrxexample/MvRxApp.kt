package com.example.mvrxexample

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class MvRxApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initStetho()
    }

    private fun initKoin(){

    }
    private fun initStetho(){
        if(BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}