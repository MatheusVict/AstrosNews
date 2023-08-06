package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.core.RemoteException
import dev.matheusvict.astrosnews.core.Resource
import dev.matheusvict.astrosnews.data.entities.database.PostDb
import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.entities.network.PostDTO
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun listPosts(category: String): Flow<Resource<List<Post>>>

    suspend fun listPostTitleContains(category: String, titleContains: String?): Flow<Resource<List<Post>>>
}