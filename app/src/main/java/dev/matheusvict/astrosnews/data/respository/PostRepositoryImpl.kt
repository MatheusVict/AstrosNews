package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.core.RemoteException
import dev.matheusvict.astrosnews.data.model.Launch
import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.data.services.SpaceFlightNewServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PostRepositoryImpl(private val service: SpaceFlightNewServices): PostRepository {

    override suspend fun listPosts(category: String): Flow<List<Post>> = flow {
        try {
            val postList = service.listPosts(category)
            emit(postList)
        } catch (ex: HttpException) {
            throw RemoteException("Unable to connect to SpaceFlight News Api")
        }

    }

    override suspend fun listPostTitleContains(
        category: String,
        titleContains: String?
    ): Flow<List<Post>> = flow {
        try {
            val postList = service.listPostsTitleContains(category, titleContains)

            emit(postList)
        } catch (ex: HttpException) {
            throw RemoteException("Unable to connect to SpaceFlight News Api")
        }
    }

}