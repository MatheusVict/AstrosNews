package dev.matheusvict.astrosnews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.matheusvict.astrosnews.data.dao.PostDao
import dev.matheusvict.astrosnews.data.entities.PostDbConverters
import dev.matheusvict.astrosnews.data.entities.database.LaunchDb
import dev.matheusvict.astrosnews.data.entities.database.PostDb

@Database(
    entities = [PostDb::class, LaunchDb::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PostDbConverters::class)
abstract class PostDatabase : RoomDatabase() {
    abstract val dao: PostDao

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java,
                        "posts_cache"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}