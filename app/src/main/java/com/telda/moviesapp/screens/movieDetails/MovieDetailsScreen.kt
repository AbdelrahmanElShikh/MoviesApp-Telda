package com.telda.moviesapp.screens.movieDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.telda.moviesapp.R
import com.telda.moviesapp.screens.movieDetails.sections.MovieContributorsSection
import com.telda.moviesapp.screens.movieDetails.sections.MovieDetailsSection
import com.telda.moviesapp.screens.movieDetails.sections.SimilarMoviesSection
import com.telda.moviesapp.uiState.Status

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.screens.movieDetails
 */
@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieName: String,
) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val inWatchList = viewModel.state.inWatchList
    Scaffold(topBar = {
        MovieDetailsTopBar(title = movieName, onBackClick = {
            navController.navigateUp()
        })
    },
        floatingActionButton = {
            val text = stringResource(id = if (inWatchList) R.string.action_delete_from_watchlist else R.string.action_add_to_watchlist)
            val icon = if (inWatchList) Icons.Filled.Delete else Icons.Filled.Add
            FABWatchList(
                onClick = {
                    when (inWatchList) {
                        true -> viewModel.handleEvents(MovieDetailsUiEvents.OnDeleteMovieToWatchList)
                        false -> viewModel.handleEvents(MovieDetailsUiEvents.OnAddMovieToWatchList)
                    }
                },
                icon = { Icon(imageVector = icon, contentDescription = "watchlist Icon") },
                text = text
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            if (viewModel.state.movieDetails.uiStatus is Status.Loading ||
                viewModel.state.similarMovies.uiStatus is Status.Loading ||
                viewModel.state.contributors.uiStatus is Status.Loading
            )
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            MovieDetailsSection(movieDetailsState = viewModel.state.movieDetails)
            Spacer(modifier = Modifier.height(8.dp))
            SimilarMoviesSection(similarMoviesUiState = viewModel.state.similarMovies)
            Spacer(modifier = Modifier.height(8.dp))
            MovieContributorsSection(movieContributors = viewModel.state.popularContributors)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(title = {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
    },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        })
}

@Composable
fun FABWatchList(onClick: () -> Unit, icon: @Composable () -> Unit, text: String) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        icon = icon,
        text = { Text(text = text) },
        expanded = true
    )
}
