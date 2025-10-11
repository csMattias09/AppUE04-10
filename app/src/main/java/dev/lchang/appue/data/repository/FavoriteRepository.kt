package dev.lchang.appue.data.repository

import dev.lchang.appue.data.local.FavouriteCountryDao
import dev.lchang.appue.data.local.FavouriteCountryEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao: FavouriteCountryDao){

    suspend fun insert(country: FavouriteCountryEntity)
        = dao.insertFavouriteCountry(country)

    suspend fun delete(country: FavouriteCountryEntity)
        = dao.deleteFavouriteCountry(country)

    fun getAll(): Flow<List<FavouriteCountryEntity>>
        = dao.getAllFavouriteCountries()




}