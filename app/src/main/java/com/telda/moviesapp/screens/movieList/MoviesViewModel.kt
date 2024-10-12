package com.telda.moviesapp.screens.movieList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telda.domain.model.MovieOverview
import com.telda.domain.result.Result
import com.telda.domain.result.error.DataError
import com.telda.domain.usecase.CombineInWatchListToMovieUseCase
import com.telda.domain.usecase.GetMovieSearchResultUseCase
import com.telda.domain.usecase.GetPopularMoviesUseCase
import com.telda.domain.usecase.GetYearFromReleaseDateUseCase
import com.telda.moviesapp.combosables.asUiText
import com.telda.moviesapp.uiState.Status
import com.telda.moviesapp.uiState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp
 */

@HiltViewModel
@OptIn(FlowPreview::class)
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieSearchResultUseCase: GetMovieSearchResultUseCase,
    private val getYearFromReleaseDateUseCase: GetYearFromReleaseDateUseCase,
    private val combineInWatchListToMovieUseCase: CombineInWatchListToMovieUseCase,
) : ViewModel() {
    var state by mutableStateOf(MoviesState())
        private set

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        observeSearchQuery()
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(moviesWithYears = UiState(Status.Loading()))
            state = when (val result = getPopularMoviesUseCase()) {
                is Result.Error -> state.copy(moviesWithYears = UiState(Status.Error(result.error.asUiText())))
                is Result.Success -> state.copy(
                    movies = result.data.results,
                    moviesWithYears = UiState(Status.Success(combineInWatchListToMovieUseCase(result.data.results).groupBy {
                        getYearFromReleaseDateUseCase(
                            it.releaseDate
                        )
                    }))
                )
            }
        }
    }

    private fun searchForMoviesByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(moviesWithYears = UiState(Status.Loading()))
            state = when (val result = getMovieSearchResultUseCase(query)) {
                is Result.Error -> state.copy(moviesWithYears = UiState(Status.Error(result.error.asUiText())))
                is Result.Success -> {
                    if (result.data.results.isNotEmpty())
                        state.copy(
                            movies = result.data.results,
                            moviesWithYears = UiState(Status.Success(combineInWatchListToMovieUseCase(result.data.results).groupBy {
                                getYearFromReleaseDateUseCase(
                                    it.releaseDate
                                )
                            }))
                        )
                    else
                        state.copy(moviesWithYears = UiState(Status.Error(DataError.NetworkError.NO_MOVIES_AVAILABLE.asUiText())))
                }
            }
        }
    }

    fun handleEvents(event: MovieListUiEvents) {
        when (event) {
            is MovieListUiEvents.OnSearchQueryChange -> {
                onQueryChanged(query = event.newQuery)
            }
        }
    }

    fun refreshState() {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(
                moviesWithYears = UiState(Status.Success(combineInWatchListToMovieUseCase(state.movies).groupBy {
                    getYearFromReleaseDateUseCase(
                        it.releaseDate
                    )
                }))
            )
        }

    }

    private fun onQueryChanged(query: String) {
        _searchText.value = query
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchText
                .debounce(1000)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isEmpty())
                        getPopularMovies()
                    else
                        searchForMoviesByQuery(query)
                }
        }
    }
}

data class MoviesState(
    val movies: List<MovieOverview> = emptyList(),
    val moviesWithYears: UiState<Map<String, List<MovieOverview>>> = UiState(Status.Loading())

)

sealed interface MovieListUiEvents {
    data class OnSearchQueryChange(val newQuery: String) : MovieListUiEvents
}
