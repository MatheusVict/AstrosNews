package dev.matheusvict.astrosnews

import android.app.Application
import dev.matheusvict.astrosnews.data.di.DataModule
import dev.matheusvict.astrosnews.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        PresentationModule.load()
        DataModule.load()
    }
}