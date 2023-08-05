package dev.matheusvict.astrosnews.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.matheusvict.astrosnews.data.entities.database.PostDb
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<PostDb>)

    @Query("SELECT * FROM post")
    fun listPosts(): Flow<List<PostDb>>

    @Query("DELETE FROM post")
    suspend fun clearDb()
}