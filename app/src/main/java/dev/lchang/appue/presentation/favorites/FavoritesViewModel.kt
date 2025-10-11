package dev.lchang.appue.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lchang.appue.data.local.FavouriteCountryEntity
import dev.lchang.appue.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel
    (private val repository: FavoriteRepository ) : ViewModel() {
        val favorites: StateFlow<List<FavouriteCountryEntity>>
        = repository.getAll().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    fun insertFavorite(country: FavouriteCountryEntity){
        viewModelScope.launch {
            repository.insert(country)
        }
    }

    fun deleteFavorite(country: FavouriteCountryEntity){
        viewModelScope.launch {
            repository.delete(country)
        }
    }


}
