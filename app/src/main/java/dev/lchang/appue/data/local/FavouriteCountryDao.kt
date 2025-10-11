package dev.lchang.appue.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCountryDao {

    //INSERT FAVOURITECOUNTRYENTITY
    @Insert
    suspend fun insertFavouriteCountry(favouriteCountryEntity: FavouriteCountryEntity)

    //DELETE FAVOURITECOUNTRYENTITY
    @Delete
    suspend fun deleteFavouriteCountry(favouriteCountryEntity: FavouriteCountryEntity)

    //GET ALL FAVOURITECOUNTRYENTITY
    @Query("SELECT * FROM favourite_countries")
    fun getAllFavouriteCountries(): Flow<List<FavouriteCountryEntity>>


}