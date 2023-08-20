package dev.upaya.shf.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.upaya.shf.BuildConfig

import timber.log.Timber


@HiltAndroidApp
class SHFApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
