package dev.matheusvict.astrosnews.domain.di

import dev.matheusvict.astrosnews.domain.GetLatestPostUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(userCaseModule())
    }

    private fun userCaseModule(): Module {
        return module {
            factory { GetLatestPostUseCase(get()) }
        }
    }
}