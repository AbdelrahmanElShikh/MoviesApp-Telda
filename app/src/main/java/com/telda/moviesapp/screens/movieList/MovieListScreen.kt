package com.telda.moviesapp.screens.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.telda.domain.model.MovieOverview
import com.telda.moviesapp.combosables.LoadingWithCircularProgressBarCentered
import com.telda.moviesapp.screens.ScreenMovieDetails
import com.telda.moviesapp.uiState.Status

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp.screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListsScreen(navController: NavController) {
    val viewModel: MoviesViewModel = hiltViewModel()
    val searchText by viewModel.searchText.collectAsState()

    Column {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp),
            query = searchText,
            onQueryChange = { viewModel.handleEvents(MovieListUiEvents.OnSearchQueryChange(it)) },
            onSearch = {},
            placeholder = {
                Text(text = "Search movies")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            active = false,
            shape = SearchBarDefaults.dockedShape,
            onActiveChange = {},
            content = {}
        )
        when (val state = viewModel.state.uiStatus) {
            is Status.Loading -> LoadingWithCircularProgressBarCentered()
            is Status.Success -> {
                MovieListsContent(
                    moviesWithYear = state.data,
                    navController = navController
                )
            }

            is Status.Error -> Text(text = state.error.asString())
        }

    }
}

@Composable
fun MovieListsContent(
    moviesWithYear: Map<String, List<MovieOverview>>?,
    navController: NavController
) {
    moviesWithYear?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxWidth(),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = StaggeredGridCells.Adaptive(minSize = 128.dp)
            ) {
                moviesWithYear.forEach { (year, movies) ->
                    item(span = StaggeredGridItemSpan.FullLine) {
                        Text(
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            text = year
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    items(movies) { movie ->
                        MovieItem(movieOverview = movie, onMovieClick = {
                            navController.navigate(ScreenMovieDetails(movieId = movie.id, movieName = movie.title))
                        })
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }


        }
    }
}

