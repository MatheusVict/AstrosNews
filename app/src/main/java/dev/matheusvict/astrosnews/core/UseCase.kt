package dev.matheusvict.astrosnews.core

import kotlinx.coroutines.flow.Flow

abstract class UseCase<Param, Source> {

    abstract suspend fun execute(param: Param): Flow<Source>

    suspend operator fun invoke(param: Param) = execute(param)

    abstract class NoParam<Source>: UseCase<Nothing, Source>() {
        abstract suspend fun execute(): Flow<Source>

        final override suspend fun execute(param: Nothing): Flow<Source> {
            throw UnsupportedOperationException()
        }

        suspend operator fun invoke() = execute()
    }
}