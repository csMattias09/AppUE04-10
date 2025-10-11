package dev.lchang.appue.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite_countries")
data class FavouriteCountryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val ranking: Int,
    val imageUrl: String
)


