package dev.matheusvict.astrosnews.core

import dev.matheusvict.astrosnews.data.model.Post

sealed class State<out T: Any> {
    /*
    * O estado de Loading poder ser um object porque não possui atributos.
    * */
    object Loading: State<Nothing>()

    data class Success<T: Any>(val result: T): State<T>()

    data class Error(val error: Throwable): State<Nothing>()
}