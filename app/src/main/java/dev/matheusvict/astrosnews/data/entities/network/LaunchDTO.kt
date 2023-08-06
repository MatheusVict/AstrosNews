package dev.matheusvict.astrosnews.data.entities.network

import dev.matheusvict.astrosnews.data.entities.database.LaunchDb
import dev.matheusvict.astrosnews.data.entities.model.Launch

data class LaunchDTO(
    val id: String,
    val provider: String
) {
    fun toModel(): Launch =
        Launch(
            id,
            provider
        )

    fun toDb(): LaunchDb =
        LaunchDb(
            id,
            provider
        )
}

fun Array<LaunchDTO>.toModel(): Array<Launch> =
    this.map {
        it.toModel()
    }.toTypedArray()

fun Array<LaunchDTO>.toDb(): Array<LaunchDb> =
    this.map {
        it.toDb()
    }.toTypedArray()