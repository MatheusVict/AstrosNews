package dev.matheusvict.astrosnews.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.matheusvict.astrosnews.data.entities.model.Launch

@Entity(tableName = "launches")
data class LaunchDb(
    @PrimaryKey
    val id: String,
    val provider: String
) {
    fun toModel(): Launch =
        Launch(
            id = id,
            provider = provider
        )
}

fun Array<LaunchDb>.toModel(): Array<Launch> =
    this.map {
        it.toModel()
    }.toTypedArray()
