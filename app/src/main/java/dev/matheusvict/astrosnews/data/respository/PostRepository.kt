package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.core.Resource
import dev.matheusvict.astrosnews.data.entities.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun listPosts(category: String): Flow<Resource<List<Post>>>

    suspend fun listPostTitleContains(category: String, titleContains: String?): Flow<List<Post>>
    abstract fun networkBoundResource(category: String): Flow<Resource<List<Post>>>
}