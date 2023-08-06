package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.core.RemoteException
import dev.matheusvict.astrosnews.core.Resource
import dev.matheusvict.astrosnews.core.networkBoundResource
import dev.matheusvict.astrosnews.data.dao.PostDao
import dev.matheusvict.astrosnews.data.entities.database.toModel
import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.entities.network.PostDTO
import dev.matheusvict.astrosnews.data.entities.network.toDb
import dev.matheusvict.astrosnews.data.services.SpaceFlightNewServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(private val service: SpaceFlightNewServices, private val dao: PostDao) :
    PostRepository {

    private val readFromDatabase = {
        dao.listPosts().map {
            it.sortedBy { postDb ->
                postDb.publishedAt
            }.reversed().toModel()
        }
    }

    private val clearDatabaseAndSave: suspend (List<PostDTO>) -> Unit = { listPostDTO ->
        dao.clearDb()
        dao.saveAll(listPostDTO.toDb())
    }

    override suspend fun listPosts(category: String): Flow<Resource<List<Post>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = {
                service.listPosts(category)
            },
            saveFetchResult = { listPostDTO ->
                clearDatabaseAndSave(listPostDTO)
            },
            onError = {
                RemoteException("Could not connect to SpaceFlight News API. Displaying cached data.")
            }
        )

    override suspend fun listPostTitleContains(
        category: String,
        titleContains: String?
    ): Flow<Resource<List<Post>>> = networkBoundResource(
        query = readFromDatabase,
        fetch = {
            service.listPostsTitleContains(category, titleContains)
        },
        saveFetchResult = { listPostDTO ->
            clearDatabaseAndSave(listPostDTO)
        },
        onError = {
            RemoteException("Could not connect to SpaceFlight News API. Displaying cached data.")
        }
    )

}