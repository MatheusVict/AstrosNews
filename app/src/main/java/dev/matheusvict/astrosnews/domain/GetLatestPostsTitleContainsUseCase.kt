package dev.matheusvict.astrosnews.domain

import dev.matheusvict.astrosnews.core.Query
import dev.matheusvict.astrosnews.core.UseCase
import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.respository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetLatestPostsTitleContainsUseCase(private val repository: PostRepository) :
    UseCase<Query, List<Post>>() {
    override suspend fun execute(param: Query): Flow<List<Post>> =
        repository.listPostTitleContains(param.type, param.option)


}