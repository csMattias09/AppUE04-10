package dev.lchang.appue.presentation.components

import android.R
import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.lchang.appue.data.model.CountryModel

@Composable
fun CountryList(
    countries: List<CountryModel>,
    favorites: List<String>,
    onToggleFavorite: ((CountryModel) -> Unit)? = null
) {
    LazyColumn {
        items(countries) { country ->
            val isFavorite = favorites.contains(country.name)

            Card(
                modifier = Modifier.fillMaxSize().padding(vertical =8.dp)
            ){
                Row(modifier = Modifier.padding(12.dp)) {
                    Image(
                        contentDescription = country.name,
                        modifier = Modifier.size(64.dp),
                        //contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(country.imageUrl)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column{
                        Text(text = country.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Ranking FIFA: ${country.ranking}")
                    }

                    //FAVORITES
                    onToggleFavorite?.let{
                        IconButton(
                            onClick = {it(country)}
                        ) {
                            Icon (imageVector = if(isFavorite) Icons.Filled.Star else Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorito",
                            )
                        }
                    }
                }
            }

        }
    }
}