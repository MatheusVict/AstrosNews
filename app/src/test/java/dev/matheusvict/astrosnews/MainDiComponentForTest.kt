package dev.matheusvict.astrosnews

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

fun configureTestAppComponent() = startKoin {
    loadKoinModules(configurerDataModuleForTest(BASE_URL) + configureDomainModuleForTest())
}