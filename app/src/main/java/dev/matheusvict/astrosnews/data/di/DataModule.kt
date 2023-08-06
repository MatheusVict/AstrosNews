package dev.matheusvict.astrosnews.data.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.matheusvict.astrosnews.data.database.PostDatabase
import dev.matheusvict.astrosnews.data.respository.PostRepository
import dev.matheusvict.astrosnews.data.respository.PostRepositoryImpl
import dev.matheusvict.astrosnews.data.services.SpaceFlightNewServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"
    private const val OK_HTTP = "Okk http"

    fun load() {
        loadKoinModules(postModule() + networkModule() + daoModule())
    }

    private fun daoModule(): Module {
        return module {
            single { PostDatabase.getInstance(androidContext()).dao }
        }
    }

    private fun postModule(): Module {
        return module {
            single<PostRepository> { PostRepositoryImpl(service = get(), dao = get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<SpaceFlightNewServices> { createService(get(), get()) }

            single {
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            }

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private inline fun <reified T> createService(
        factory: Moshi,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }
}