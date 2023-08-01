package dev.matheusvict.astrosnews.data.di

import dev.matheusvict.astrosnews.data.respository.MockAPIService
import dev.matheusvict.astrosnews.data.respository.PostRepository
import dev.matheusvict.astrosnews.data.respository.PostRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

    fun load() {
        loadKoinModules(postModule())
    }

    private fun postModule(): Module {
        return module {
            single<PostRepository> { PostRepositoryImpl(MockAPIService) }
        }
    }
}