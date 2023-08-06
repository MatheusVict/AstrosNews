package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.core.RemoteException
import dev.matheusvict.astrosnews.core.Resource
import dev.matheusvict.astrosnews.data.dao.PostDao
import dev.matheusvict.astrosnews.data.entities.database.toModel
import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.entities.network.toDb
import dev.matheusvict.astrosnews.data.entities.network.toModel
import dev.matheusvict.astrosnews.data.services.SpaceFlightNewServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PostRepositoryImpl(private val service: SpaceFlightNewServices, private val dao: PostDao) :
    PostRepository {
    override suspend fun listPosts(category: String): Flow<Resource<List<Post>>> = networkBoundResource(category)

    /* override suspend fun listPosts(category: String): Flow<List<Post>> = flow {
         try {
             val postList = service.listPosts(category).toModel()
             emit(postList)
         } catch (ex: HttpException) {
             throw RemoteException("Unable to connect to SpaceFlight News Api")
         }

     }*/

    override suspend fun listPostTitleContains(
        category: String,
        titleContains: String?
    ): Flow<List<Post>> = flow {
        try {
            val postList = service.listPostsTitleContains(category, titleContains).toModel()

            emit(postList)
        } catch (ex: HttpException) {
            throw RemoteException("Unable to connect to SpaceFlight News Api")
        }
    }

    override fun networkBoundResource(category: String): Flow<Resource<List<Post>>> = flow {
        var data = dao.listPosts().first()

        try {
            with(service.listPosts(category)) {
                if(this.isNotEmpty()) {
                    dao.clearDb()
                    dao.saveAll(this.toDb())
                }

                data = dao.listPosts().first()
            }
        } catch (ex: Exception) {
            val error = RemoteException("Could not cooncet to the API. Displaying cached data")
            emit(Resource.Error(data.toModel(), error))
        }
        emit(Resource.Success(data.toModel()))
    }

}