package com.example.mvrxexample.di.network

import com.example.mvrxexample.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

private const val BASE_URL = "https://api.ratesapi.io/api/"
private const val OK_HTTP_CACHE_SIZE = 10 * 1024 * 1024L
private const val OK_HTTP_CACHE_DIRECTORY_NAME = "OkHttpCache"

internal val retrofitModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }
    single { StethoInterceptor() }

    single { File(androidApplication().cacheDir, OK_HTTP_CACHE_DIRECTORY_NAME) }

    single { Cache(get<File>(), OK_HTTP_CACHE_SIZE) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptorOnDebugBuild(get<HttpLoggingInterceptor>())
            .addInterceptorOnDebugBuild(get<StethoInterceptor>())
            .cache(get<Cache>())
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}

private fun OkHttpClient.Builder.addInterceptorOnDebugBuild(interceptor: Interceptor) =
    if (BuildConfig.DEBUG)
        addNetworkInterceptor(interceptor)
    else this