package com.telda.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telda.domain.model.Movies
import com.telda.domain.result.Result
import com.telda.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {
    var state by mutableStateOf(MoviesState())
        private set
    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when (val result = getPopularMoviesUseCase()) {
                is Result.Error -> state.copy(isLoading = false, error = result.error.asUiText())
                is Result.Success -> state.copy(isLoading = false, movies = result.data)
            }
        }
    }
}

sealed interface NavigationEvent {
    object NavigateToDetails : NavigationEvent
}

data class MoviesState(
    val isLoading: Boolean = false, val movies: Movies? = null, val error: UiText? = null
)
