package dev.matheusvict.astrosnews.data.entities

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.matheusvict.astrosnews.data.entities.database.LaunchDb

class PostDbConverters {

    @TypeConverter
    fun fromString(string: String): Array<LaunchDb>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Array<LaunchDb>::class.java)
        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun toString(array: Array<LaunchDb>) : String? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Array<LaunchDb>::class.java)
        return jsonAdapter.toJson(array)
    }
}