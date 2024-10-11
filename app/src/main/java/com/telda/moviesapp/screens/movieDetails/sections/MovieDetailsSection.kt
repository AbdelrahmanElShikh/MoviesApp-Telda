package com.telda.moviesapp.screens.movieDetails.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.telda.data.BuildConfig
import com.telda.domain.model.MovieDetails
import com.telda.moviesapp.R
import com.telda.moviesapp.uiState.Status
import com.telda.moviesapp.uiState.UiState
import com.telda.moviesapp.utils.formatMoney

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.moviesapp.screens.movieDetails
 */
@Composable
fun MovieDetailsSection(movieDetailsState: UiState<MovieDetails>) {
    if (movieDetailsState.uiStatus is Status.Success && movieDetailsState.uiStatus.data != null) {
        val movieDetails = movieDetailsState.uiStatus.data
        Column {
            AsyncImage(
                model = "${BuildConfig.IMAGE_BASE_URL}${movieDetails.backdropPath}",
                contentDescription = "Movie backdrop Path",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.30f)
                    .clip(RoundedCornerShape(bottomStart = 64.dp, bottomEnd = 64.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.30f)
                        .wrapContentHeight()
                        .offset(y = (-50).dp, x = 16.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 4.dp
                    ),
                ) {
                    AsyncImage(
                        model = "${BuildConfig.IMAGE_BASE_URL}${movieDetails.posterPath}",
                        contentDescription = "Movie Poster",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(modifier = Modifier.padding(start = 32.dp, top = 16.dp)) {
                    Text(
                        text = movieDetails.title,
                        style = TextStyle(fontSize = MaterialTheme.typography.headlineMedium.fontSize, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stringResource(id = R.string.label_revenue)} ${(movieDetails.revenue).formatMoney()}",
                        style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize, fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${stringResource(id = R.string.label_release_date)} ${movieDetails.releaseDate}",
                        style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize, fontWeight = FontWeight.Medium)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }


            }

            Text(
                modifier = Modifier.offset(y = (-30).dp, x = 16.dp),
                text = stringResource(id = R.string.label_overview),
                style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier
                    .offset(y = (-30).dp)
                    .padding(horizontal = 16.dp),
                text = movieDetails.overview,
                style = TextStyle(fontSize = MaterialTheme.typography.labelLarge.fontSize),
            )

        }

    }
}
