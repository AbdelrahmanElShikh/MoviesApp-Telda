package com.telda.moviesapp.screens.movieList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telda.domain.model.Movie
import com.telda.domain.result.Result
import com.telda.domain.usecase.GetMovieSearchResultUseCase
import com.telda.domain.usecase.GetPopularMoviesUseCase
import com.telda.domain.usecase.GetYearFromReleaseDateUseCase
import com.telda.moviesapp.combosables.asUiText
import com.telda.moviesapp.uiState.Status
import com.telda.moviesapp.uiState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {
    var state by mutableStateOf(UiState<Map<String, List<Movie>>>())
        private set

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        observeSearchQuery()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            state = state.copy(uiStatus = Status.Loading())
            state = when (val result = getPopularMoviesUseCase()) {
                is Result.Error -> state.copy(uiStatus = Status.Error(result.error.asUiText()))
                is Result.Success -> state.copy(
                    uiStatus = Status.Success(result.data.results.groupBy { getYearFromReleaseDateUseCase(it.releaseDate) })
                )
            }
        }
    }

    private fun searchForMoviesByQuery(query: String) {
        viewModelScope.launch {
            state = state.copy(uiStatus = Status.Loading())
            state = when (val result = getMovieSearchResultUseCase(query)) {
                is Result.Error -> state.copy(uiStatus = Status.Error(result.error.asUiText()))
                is Result.Success -> state.copy(
                    uiStatus = Status.Success(result.data.results.groupBy { getYearFromReleaseDateUseCase(it.releaseDate) })
                )
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

sealed interface MovieListUiEvents {
    data class OnSearchQueryChange(val newQuery: String) : MovieListUiEvents
}
