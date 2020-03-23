package com.touchlane.android.bootstrap

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
                postsModule,
                usersModule,
                userPostsModule,
                commentsModule
            )
        }
    }
}