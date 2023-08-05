package dev.matheusvict.astrosnews.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.matheusvict.astrosnews.data.entities.model.Launch
import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.entities.network.toModel

@Entity(tableName = "post")
data class PostDb(
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String,
    val launches: Array<LaunchDb> = emptyArray()
) {
    fun toModel(): Post =
        Post(
            id = id,
            title = title,
            url = url,
            imageUrl = imageUrl,
            summary = summary,
            publishedAt = publishedAt,
            updatedAt = updatedAt,
            launches = launches.toModel()
        )

}

fun List<PostDb>.toModel(): List<Post> =
    this.map {
        it.toModel()
    }
