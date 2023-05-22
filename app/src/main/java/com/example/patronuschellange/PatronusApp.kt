package com.example.patronuschellange

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PatronusApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        if(BuildConfig.DEBUG)
        Timber.plant(Timber.DebugTree())
    }
}