package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun listPosts(category: String): Flow<List<Post>>
}