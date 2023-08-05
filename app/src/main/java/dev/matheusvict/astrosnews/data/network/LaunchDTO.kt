package dev.matheusvict.astrosnews.data.network

import dev.matheusvict.astrosnews.data.model.Launch

data class LaunchDTO(
    val id: String,
    val provider: String
) {
    fun toModel(): Launch =
        Launch(
            id,
            provider
        )
}

fun Array<LaunchDTO>.toModel(): Array<Launch> =
    this.map {
        it.toModel()
    }.toTypedArray()
