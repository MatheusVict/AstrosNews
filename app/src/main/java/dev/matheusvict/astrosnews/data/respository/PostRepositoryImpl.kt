package dev.matheusvict.astrosnews.data.respository

import dev.matheusvict.astrosnews.data.model.Launch
import dev.matheusvict.astrosnews.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(private val service: MockAPIService): PostRepository {

    override suspend fun listPosts(): Flow<List<Post>> = flow {
        val postList = service.listPosts
        emit(postList)
    }

}

object MockAPIService {
    val listPosts: List<Post> = listOf(
        Post(
            id = 20189,
            title = "Scientists, using Webb, discover water vapor within a planet-forming disk",
            url = "https://www.nasaspaceflight.com/2023/07/pds70-water/",
            imageUrl = "https://www.nasaspaceflight.com/wp-content/uploads/2023/07/stsci-01h5mxgsckgd0sxc4v6mbmakdy-1170x658.jpg",
            summary = "Using the joint NASA/European Space Agency/Canadian Space Agency James Webb Space Telescope, a team of scientists has discovered, for the first time, water vapor within a planet-forming disk. The water vapor, which was found within the inner disk of two circumstellar disks around star PDS 70, is allowing scientists to research the ways by which water makes its way into rocky, terrestrial planets.",
            publishedAt = "2023-07-30T19:23:53Z",
            updatedAt = "2023-07-30T19:24:41.668000Z",
            launches = emptyList()
        ),
        Post(
            id = 20188,
            title = "Crew Dragon, Soyuz missions set for launches to ISS",
            url = "https://spacenews.com/crew-dragon-soyuz-missions-set-for-launches-to-iss/",
            imageUrl = "https://i0.wp.com/spacenews.com/wp-content/uploads/2023/07/crew7-portrait.jpg",
            summary = "Two crewed missions remain on track to launch to the International Space Station over the next month and a half after addressing technical issues that included a Soyuz coolant leak.",
            publishedAt = "2023-07-30T18:16:25Z",
            updatedAt = "2023-07-30T19:01:58.574000Z",
            launches = emptyList()
        ),
        Post(
            id = 20187,
            title = "What’s Happening in Space Policy July 30-August 5, 2023",
            url = "https://spacepolicyonline.com/news/whats-happening-in-space-policy-july-30-august-5-2023/",
            imageUrl = "https://s.w.org/images/core/emoji/14.0.0/72x72/1f324.png",
            summary = "Here is SpacePolicyOnline.com’s list of space policy events for the week of July 30-August 5, 2023 and any insight we can offer about them. The House and Senate are on […]",
            publishedAt = "2023-07-30T15:12:38Z",
            updatedAt = "2023-07-30T15:14:49.796000Z",
            launches = emptyList(),
        ),
        Post(
            id = 20186,
            title = "India launches seven satellites on PSLV rocket",
            url = "https://spacenews.com/india-launches-seven-satellites-on-pslv-rocket/",
            imageUrl = "https://i0.wp.com/spacenews.com/wp-content/uploads/2023/07/PSLV.jpg",
            summary = "India successfully launched seven Singaporean satellites into low Earth orbit July 29 on its workhorse PSLV rocket. It was the nation’s sixth orbital launch of the year, which were all successful, and occurred two weeks after the launch of the Indian robotic lunar lander Chandrayaan-3, which is flying toward the lunar south pole for a soft-landing attempt between Aug. 23 and 24.",
            publishedAt = "2023-07-30T13:49:14Z",
            updatedAt = "2023-07-30T17:04:00.921000Z",
            launches = listOf(
                Launch("af626de1-88f7-418b-8801-a4e897f067a4", "Launch Library 2")
            ),
        ),
        Post(
            id = 20185,
            title = "Congress Leaves for Summer Break With Long To-Do List When They Return",
            url = "https://spacepolicyonline.com/news/congress-leaves-for-summer-break-with-long-to-do-list-when-they-return/",
            imageUrl = "https://spacepolicyonline.com/wp-content/uploads/2023/06/Granger-Milcon-markup-June-13-23-300x148.png",
            summary = "The House and Senate left town Thursday, a day earlier than planned, eager to begin their summer break. The Senate is gone for five weeks, the House for six. While […]",
            publishedAt = "2023-07-29T21:50:26Z",
            updatedAt = "2023-07-29T21:54:56.568000Z",
            launches = emptyList(),
        ),
    )
}