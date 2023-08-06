package dev.matheusvict.astrosnews.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onError: (Throwable) -> Throwable
): Flow<Resource<ResultType>> = flow {
    var data = query().first()

    try {
        saveFetchResult(fetch())
        data = query().first()
    } catch (ex: Exception) {
        emit(Resource.Error(data, onError(ex)))
    }
    emit(Resource.Success(data))
}