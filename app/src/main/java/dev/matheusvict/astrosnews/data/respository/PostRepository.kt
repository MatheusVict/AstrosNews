package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.data.entities.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun listPosts(category: String): Flow<List<Post>>

    suspend fun listPostTitleContains(category: String, titleContains: String?): Flow<List<Post>>
}