package com.egferreira.pagtest

import android.app.Application
import com.egferreira.pagtest.di.presentationModule
import com.egferreira.pagtest.di.remoteDataSourceModule
import com.egferreira.pagtest.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            modules(
                presentationModule,
                repositoryModule,
                remoteDataSourceModule
            )
            androidContext(this@MainApplication)
        }
    }
}