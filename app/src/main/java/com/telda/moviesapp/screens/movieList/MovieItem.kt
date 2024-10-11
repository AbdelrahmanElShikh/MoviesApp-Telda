package com.telda.moviesapp.screens.movieList

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp.screens
 */
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.telda.data.BuildConfig
import com.telda.domain.model.Movie
import com.telda.moviesapp.R
import com.telda.moviesapp.combosables.TextWithIcon

@Composable
fun MovieItem(movie: Movie, onMovieClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.clickable { onMovieClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AsyncImage(
                model = "${BuildConfig.IMAGE_BASE_URL}${movie.posterPath}",
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_placeholder)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = movie.title,
                style = TextStyle(fontSize = MaterialTheme.typography.titleSmall.fontSize, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${stringResource(id = R.string.label_release_date)} ${movie.releaseDate}",
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (movie.inWatchList) {
                TextWithIcon(
                    text = stringResource(id = R.string.label_added_to_watchlist),
                    icon = Icons.TwoTone.Star,
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

