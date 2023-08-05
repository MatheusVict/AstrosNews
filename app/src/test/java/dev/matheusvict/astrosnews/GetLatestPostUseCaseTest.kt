package dev.matheusvict.astrosnews

import dev.matheusvict.astrosnews.core.Query
import dev.matheusvict.astrosnews.data.SpaceFlightNewsCategory
import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.domain.GetLatestPostUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class GetLatestPostUseCaseTest: KoinTest {
    private val getLatestPostUseCase: GetLatestPostUseCase by inject()

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            configureTestAppComponent()
        }

        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun should_Return_a_NoNullable_Object_When_Connect_To_Repository() {
        runBlocking {
            val result = getLatestPostUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))

            assertNotNull(result)
        }
    }

    @Test
    fun should_ReturnACorrectObject_When_Connect_To_Repository() {
        runBlocking {
            val result = getLatestPostUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))

            assertTrue(result is Flow<List<Post>>)
        }
    }

    @Test
    fun should_ReturnANotEmptyList_When_Connect_To_Repository() {
        runBlocking {
            val result = getLatestPostUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))

            assertFalse(result.first().isEmpty())
        }
    }
}