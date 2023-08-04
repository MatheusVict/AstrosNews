package dev.matheusvict.astrosnews.data.services

import dev.matheusvict.astrosnews.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

/*
* This interface is responsible for connection with API
* */
interface SpaceFlightNewServices {

    @GET("{type}")
    suspend fun listPosts(@Path("type") type: String): List<Post>

}