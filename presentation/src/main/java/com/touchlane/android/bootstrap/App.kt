package com.touchlane.android.bootstrap

import android.app.Application

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(
                AppModule(this@App.applicationContext)
            )
            .build()
    }

    companion object {
        private lateinit var instance: App
        fun get(): App = instance
    }
}