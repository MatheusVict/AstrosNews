package dev.matheusvict.astrosnews.core

import dev.matheusvict.astrosnews.data.entities.model.Post

sealed class State<out T: Any> {
    object Loading: State<Nothing>()

    data class Success<T: Any>(val result: T): State<T>()

    data class Error(val error: Throwable): State<Nothing>()
}