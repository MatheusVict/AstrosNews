package dev.matheusvict.astrosnews.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.matheusvict.astrosnews.data.services.SpaceFlightNewServices
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SpaceFlightNewsServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: SpaceFlightNewServices

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(factory))
            .build()
            .create(SpaceFlightNewServices::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun should_getCorrectEndpoint_WhenReceiveParams() {
        runBlocking {
            // articles endpoint
            mockWebServer.enqueue(MockResponse().setBody("[]"))

            val articlesResult = service.listPosts(SpaceFlightNewsCategory.ARTICLES.value)
            val articlesRequest = mockWebServer.takeRequest()

            assertEquals(articlesRequest.path, "/articles")

            // blogs endpoint
            mockWebServer.enqueue(MockResponse().setBody("[]"))

            val blogsResult = service.listPosts(SpaceFlightNewsCategory.BLOGS.value)
            val blogsRequest = mockWebServer.takeRequest()

            assertEquals(blogsRequest.path, "/blogs")

            // reports endpoint
            mockWebServer.enqueue(MockResponse().setBody("[]"))

            val reportsResult = service.listPosts(SpaceFlightNewsCategory.REPORTS.value)
            val reportsRequest = mockWebServer.takeRequest()

            assertEquals(reportsRequest.path, "/reports")
        }
    }

    @Test
    fun should_GetCorrectEndpoint_WhenReceivesQueryOption() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPostsTitleContains("articles", "mars")

            val request = mockWebServer.takeRequest()
            println(request.path)

            assertEquals(request.path, "/articles?title_contains=mars")
        }
    }

    @Test
    fun should_GetCorrectEndpoint_WhenReceivesNullOption() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPostsTitleContains("articles", null)

            val request = mockWebServer.takeRequest()
            println(request.path)

            assertEquals(request.path, "/articles")
        }
    }
}