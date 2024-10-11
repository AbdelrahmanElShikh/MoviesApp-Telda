package com.telda.moviesapp.screens.movieDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telda.domain.model.Contributor
import com.telda.domain.model.MovieContributors
import com.telda.domain.model.MovieDetails
import com.telda.domain.model.Movies
import com.telda.domain.result.Result
import com.telda.domain.usecase.AddMovieToWatchListUseCase
import com.telda.domain.usecase.DeleteMovieFromWatchListUseCase
import com.telda.domain.usecase.GetMostPopularMovieContributorsUseCase
import com.telda.domain.usecase.GetMovieContributorsUseCase
import com.telda.domain.usecase.GetMovieDetailsByIdUseCase
import com.telda.domain.usecase.GetSimilarMoviesByIdUseCase
import com.telda.moviesapp.combosables.asUiText
import com.telda.moviesapp.uiState.Status
import com.telda.moviesapp.uiState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.screens.movieDetails
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase,
    private val getSimilarMoviesByIdUseCase: GetSimilarMoviesByIdUseCase,
    private val getMovieContributorsUseCase: GetMovieContributorsUseCase,
    private val getMostPopularMovieContributorsUseCase: GetMostPopularMovieContributorsUseCase,
    private val addMovieToWatchListUseCase: AddMovieToWatchListUseCase,
    private val deleteMovieFromWatchListUseCase: DeleteMovieFromWatchListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(MovieDetailsState())
        private set

    init {
        val movieId = savedStateHandle["movieId"] ?: 0
        val inWatchList = savedStateHandle["inWatchList"] ?: false
        state = state.copy(movieId = movieId, inWatchList = inWatchList)
        getMovieDetails(movieId = movieId)
        getSimilarMovies(movieId = movieId)
        getMovieContributors(movieId = movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            state = state.copy(movieDetails = UiState(Status.Loading()))
            state = when (val result = getMovieDetailsByIdUseCase(movieId)) {
                is Result.Error -> state.copy(movieDetails = UiState(Status.Error(result.error.asUiText())))
                is Result.Success -> state.copy(
                    movieDetails = UiState(Status.Success(result.data))
                )
            }
        }
    }

    private fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            state = state.copy(similarMovies = UiState(Status.Loading()))
            state = when (val result = getSimilarMoviesByIdUseCase(movieId)) {
                is Result.Error -> state.copy(similarMovies = UiState(Status.Error(result.error.asUiText())))
                is Result.Success -> state.copy(
                    similarMovies = UiState(Status.Success(result.data))
                )
            }
        }
    }

    private fun getMovieContributors(movieId: Int) {
        viewModelScope.launch {
            state = state.copy(contributors = UiState(Status.Loading()))
            state = when (val result = getMovieContributorsUseCase(movieId)) {
                is Result.Error -> state.copy(contributors = UiState(Status.Error(result.error.asUiText())))
                is Result.Success -> {
                    state.copy(
                        contributors = UiState(Status.Success(result.data)),
                        popularContributors = getMostPopularMovieContributorsUseCase(result.data)
                    )
                }
            }
        }
    }

    fun handleEvents(event: MovieDetailsUiEvents) {
        state = when (event) {
            is MovieDetailsUiEvents.OnAddMovieToWatchList -> {
                addMovieToWatchList(movieId = state.movieId ?: 0)
                state.copy(inWatchList = true)
            }

            is MovieDetailsUiEvents.OnDeleteMovieToWatchList -> {
                deleteMovieFromWatchList(movieId = state.movieId ?: 0)
                state.copy(inWatchList = false)
            }
        }
    }

    private fun addMovieToWatchList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addMovieToWatchListUseCase(movieId)
        }
    }

    private fun deleteMovieFromWatchList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovieFromWatchListUseCase(movieId)
        }
    }
}

data class MovieDetailsState(
    val movieDetails: UiState<MovieDetails> = UiState(Status.Loading()),
    val similarMovies: UiState<Movies> = UiState(Status.Loading()),
    val contributors: UiState<MovieContributors> = UiState(Status.Loading()),
    val popularContributors: List<Contributor?> = listOf(),
    val movieId: Int? = null,
    val inWatchList: Boolean = false
)

sealed interface MovieDetailsUiEvents {
    data object OnAddMovieToWatchList : MovieDetailsUiEvents
    data object OnDeleteMovieToWatchList : MovieDetailsUiEvents
}
