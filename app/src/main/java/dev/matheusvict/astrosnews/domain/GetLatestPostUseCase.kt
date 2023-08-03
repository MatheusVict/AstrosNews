package dev.matheusvict.astrosnews.domain

import dev.matheusvict.astrosnews.core.UseCase
import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.data.respository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetLatestPostUseCase(private val repository: PostRepository): UseCase<List<Post>>() {

    override suspend fun execute(): Flow<List<Post>> = repository.listPosts()
}