package com.telda.moviesapp.screens.movieDetails.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.telda.domain.model.Movies
import com.telda.moviesapp.R
import com.telda.moviesapp.screens.movieList.MovieItem
import com.telda.moviesapp.uiState.Status
import com.telda.moviesapp.uiState.UiState

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.moviesapp.screens.movieDetails.sections
 */
@Composable
fun SimilarMoviesSection(similarMoviesUiState: UiState<Movies>) {
    if (similarMoviesUiState.uiStatus is Status.Success &&
        similarMoviesUiState.uiStatus.data != null &&
        similarMoviesUiState.uiStatus.data.results.isNotEmpty()
    ) {
        val movies = similarMoviesUiState.uiStatus.data.results
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.label_similar_movies),
            style = TextStyle(fontSize = MaterialTheme.typography.headlineMedium.fontSize, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.take(5)) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}
