package dev.matheusvict.astrosnews

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.matheusvict.astrosnews.data.respository.PostRepository
import dev.matheusvict.astrosnews.data.respository.PostRepositoryImpl
import dev.matheusvict.astrosnews.data.services.SpaceFlightNewServices
import dev.matheusvict.astrosnews.domain.GetLatestPostUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun configureDomainModuleForTest() = module {
    factory<GetLatestPostUseCase> { GetLatestPostUseCase(get()) }
}

fun configurerDataModuleForTest(baseUrl: String) = module {
    single {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(SpaceFlightNewServices::class.java)
    }

    single<PostRepository> { PostRepositoryImpl(get()) }
}