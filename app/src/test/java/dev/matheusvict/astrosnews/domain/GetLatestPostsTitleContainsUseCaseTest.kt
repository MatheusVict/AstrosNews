package dev.matheusvict.astrosnews.domain

import dev.matheusvict.astrosnews.configureTestAppComponent
import dev.matheusvict.astrosnews.core.Query
import dev.matheusvict.astrosnews.data.SpaceFlightNewsCategory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.test.KoinTest
import org.koin.test.inject

class GetLatestPostsTitleContainsUseCaseTest : KoinTest {

    private val getLatestPostsTitleContainsUseCase: GetLatestPostsTitleContainsUseCase by inject()
    private val type = SpaceFlightNewsCategory.ARTICLES.value
    private val searchString = "mars"

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            configureTestAppComponent()
        }

        @AfterClass
        fun tearDown() {
            GlobalContext.stopKoin()
        }
    }

    @Test
    fun should_ReturnValidResults_WhenSearch() {
        runBlocking {
            val result = getLatestPostsTitleContainsUseCase(Query(type, searchString))
            var assertion = true
            result.first().forEach { post ->
                println(post.title)
                assertion = assertion && post.title.lowercase().contains(searchString, true)
            }

            assertTrue(assertion)
        }


    }
}
