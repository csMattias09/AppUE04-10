package dev.lchang.appue.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import dev.lchang.appue.data.local.AppDataBase
import dev.lchang.appue.data.local.FavouriteCountryEntity
import dev.lchang.appue.data.model.CountryModel
import dev.lchang.appue.data.repository.FavoriteRepository
import dev.lchang.appue.presentation.components.CountryList
import dev.lchang.appue.presentation.favorites.FavoritesViewModel
import dev.lchang.appue.presentation.favorites.FavoritesViewModelFactory
import okhttp3.internal.platform.android.AndroidSocketAdapter.Companion.factory

val mockCountries = listOf(
    CountryModel("Colombia", 8,"https://flagcdn.com/w320/co.png"),
    CountryModel("Argentina", 1,"https://flagcdn.com/w320/ar.png"),
    CountryModel("Francia", 4,"https://flagcdn.com/w320/fr.png"),
    CountryModel("Brasil", 3,"https://flagcdn.com/w320/br.png"),
    CountryModel("Perú", 62,"https://flagcdn.com/w320/pe.png"),
    CountryModel("Uruguay", 9,"https://flagcdn.com/w320/uy.png")
)

@Composable
fun HomeScreen()
{
    val context = LocalContext.current
    val db = remember{ AppDataBase.getInstance(context) }
    val repository = remember{ FavoriteRepository(db.favouriteCountryDo) }
    val viewModel: FavoritesViewModel = viewModel (factory = FavoritesViewModelFactory(repository))
    val favorites by viewModel.favorites.collectAsState()
    val favoritenames = favorites.map { it.name }


    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    )
    {
        //Bienvenido a la pantalla principal
        Text(text = "Ranking FIFA 2025")
        Spacer(modifier = Modifier.height(8.dp))

        CountryList(
            countries = mockCountries,
            favorites = favoritenames,
            onToggleFavorite = { country ->
                val isFav = favoritenames.contains(country.name)
                if (isFav) {
                    favorites.find { it.name == country.name }?.let {
                        viewModel.deleteFavorite(it)
                    }
                }else{
                    viewModel.insertFavorite(
                        FavouriteCountryEntity(
                            name = country.name,
                            ranking = country.ranking,
                            imageUrl = country.imageUrl

                        )
                    )

                }
            }


        )

    }
}

