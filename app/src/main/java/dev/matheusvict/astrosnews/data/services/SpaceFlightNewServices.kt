package dev.matheusvict.astrosnews.data.services

import dev.matheusvict.astrosnews.data.model.Post
import retrofit2.http.GET

/*
* This interface is responsible for connection with API
* */
interface SpaceFlightNewServices {

    @GET("articles")
    suspend fun listPosts(): List<Post>
}