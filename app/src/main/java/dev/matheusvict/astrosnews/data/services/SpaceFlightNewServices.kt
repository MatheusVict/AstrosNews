package dev.matheusvict.astrosnews.data.services

import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.data.network.PostDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SpaceFlightNewServices {

    @GET("{type}")
    suspend fun listPosts(@Path("type") type: String): List<PostDTO>

    @GET("{type}")
    suspend fun listPostsTitleContains(
        @Path("type") type: String,
        @Query("title_contains") titleContains: String?
    ): List<PostDTO>

}