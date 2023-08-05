package dev.matheusvict.astrosnews

import dev.matheusvict.astrosnews.data.entities.database.LaunchDb
import dev.matheusvict.astrosnews.data.entities.database.PostDb
import org.junit.Before

open class DbTest {

    lateinit var dbPost: List<PostDb>

    @Before
    fun createPostsForTest() {
        val postNoLaunches = PostDb(
            id = 12783,
            title = "SpaceX launches 60 more Starlink satellites, claims over 500,000 service pre-orders so far",
            url = "https://www.teslarati.com/spacex-starlink-500k-pre-orders-60-more-satellites-launch/",
            imageUrl = "https://www.teslarati.com/wp-content/uploads/2021/03/Starlink-21-v1.0-L17-20210314-1-1024x576.jpg",
            summary = "SpaceX has successfully launched its 21st operational Starlink mission, adding 60 more satellites to the company’s growing constellation and bringing the total number of Starlink satellites launched to date to 1383.",
            publishedAt = "2021-03-14T13:00:00Z",
            updatedAt = "2021-03-14T13:00:00Z",
            launches = emptyArray()
        )

        val postWithLaunches = PostDb(
            id = 12783,
            title = "SpaceX launches 60 more Starlink satellites, claims over 500,000 service pre-orders so far",
            url = "https://www.teslarati.com/spacex-starlink-500k-pre-orders-60-more-satellites-launch/",
            imageUrl = "https://www.teslarati.com/wp-content/uploads/2021/03/Starlink-21-v1.0-L17-20210314-1-1024x576.jpg",
            summary = "SpaceX has successfully launched its 21st operational Starlink mission, adding 60 more satellites to the company’s growing constellation and bringing the total number of Starlink satellites launched to date to 1383.",
            publishedAt = "2021-03-14T13:00:00Z",
            updatedAt = "2021-03-14T13:00:00Z",
            launches = arrayOf(
                LaunchDb(
                    id = "5eb87d46ffd86e000604b32a",
                    provider = "Launch Library 2",
                )
            )
        )

        dbPost = listOf(postNoLaunches, postWithLaunches)
    }
}