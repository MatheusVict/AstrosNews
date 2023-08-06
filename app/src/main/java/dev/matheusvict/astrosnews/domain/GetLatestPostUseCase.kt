package dev.matheusvict.astrosnews.domain

import dev.matheusvict.astrosnews.core.Query
import dev.matheusvict.astrosnews.core.Resource
import dev.matheusvict.astrosnews.core.UseCase
import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.respository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetLatestPostUseCase(private val repository: PostRepository): UseCase<Query ,Resource<List<Post>>>() {

    override suspend fun execute(param: Query): Flow<Resource<List<Post>>> = repository.listPosts(param.type)
}