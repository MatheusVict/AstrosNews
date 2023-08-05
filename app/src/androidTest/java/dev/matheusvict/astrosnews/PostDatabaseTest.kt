package dev.matheusvict.astrosnews

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.matheusvict.astrosnews.data.dao.PostDao
import dev.matheusvict.astrosnews.data.database.PostDatabase
import dev.matheusvict.astrosnews.data.entities.database.LaunchDb
import dev.matheusvict.astrosnews.data.entities.database.PostDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PostDatabaseTest: DbTest() {

    private lateinit var dao: PostDao
    private lateinit var postDatabase: PostDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        postDatabase = Room.inMemoryDatabaseBuilder(
            context = context,
            PostDatabase::class.java
        ).build()
        dao = postDatabase.dao

    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        postDatabase.close()
    }

    @Test
    fun should_SavesPostsOnDatabase_WhenReceivesPostList() {
        // verify if the database is empty
        lateinit var result: List<PostDb>

        runBlocking {
            result = dao.listPosts().first()
        }
        assertTrue(result.isEmpty())

        // save posts on database
        runBlocking {
            dao.saveAll(dbPost)
            result = dao.listPosts().first()
        }
        // verify if the database is not empty

        assertTrue(result.isNotEmpty())
    }

    @Test
    fun should_ReturnsPostList_WhenReadsDatabase() {
        lateinit var result: PostDb
        // save posts on database
        runBlocking {
            dao.clearDb()
            dao.saveAll(dbPost.subList(0, 1))
            result = dao.listPosts().first()[0]
        }

        // read posts from database

        // verify if the posts are the same
        assertTrue(result.title == dbPost[0].title)
    }

    @Test
    fun should_CleanDatabase_WhenCallsClearDb() {
        lateinit var result: List<PostDb>
        // save posts on database
        runBlocking {

            dao.saveAll(dbPost)
            result = dao.listPosts().first()
        }

        // verify if the database is not empty
        assertTrue(result.isNotEmpty())

        // clean database
        runBlocking {

            dao.clearDb()
            result = dao.listPosts().first()
        }

        // verify if the database is empty
        assertTrue(result.isEmpty())
    }

    @Test
    fun test() {
        assertTrue(2 + 2 != 5)
    }
}