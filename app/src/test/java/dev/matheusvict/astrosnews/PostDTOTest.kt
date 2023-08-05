package dev.matheusvict.astrosnews

import dev.matheusvict.astrosnews.data.entities.model.Post
import dev.matheusvict.astrosnews.data.entities.network.LaunchDTO
import dev.matheusvict.astrosnews.data.entities.network.PostDTO
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class PostDTOTest {

    private val launchDto = LaunchDTO(
        id = "0d779392-1a36-4c1e-b0b8-ec11e3031ee6",
        provider = "Launch Library 2"
    )

    private val postDTO = PostDTO(
        id = 12783,
        title = "SpaceX ready for back to back astronaut, Starlink launches",
        url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
        imageUrl = "https://www.teslarati.com/wp-content/uploads/2021/11/Crew-3-Dragon-C210-F9-B1067-39A-110921-Richard-Angle-feature-2-c.jpg",
        summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut and Starlink satellite launches later this week. Both SpaceX East...",
        publishedAt = "2021-11-10T10:07:44.000Z",
        updatedAt = "2021-11-10T10:08:01.340Z",
        launches = arrayOf(launchDto)
    )

    @Test
    fun `should transform correctly entity in a model`() {
        val post: Post = postDTO.toModel()

        assertTrue(post is Post)
        assertTrue(post.title == postDTO.title)
        assertTrue(post.launches.isNotEmpty())
    }

}