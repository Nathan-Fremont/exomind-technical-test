package com.exomind.technical_test.ui

import android.app.Application
import com.exomind.technical_test.BuildConfig
import com.exomind.technical_test.repository.di.koinRepositoryModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@MyApplication)
//            modules(koinDomainModules)
            modules(koinRepositoryModules)
//            modules(koinUiModules)
        }
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}