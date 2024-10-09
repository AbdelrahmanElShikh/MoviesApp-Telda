package com.telda.domain.repository

import com.telda.domain.model.Movies
import com.telda.domain.result.Result
import com.telda.domain.result.error.DataError


interface MoviesRepository {
    suspend fun getPopularMovies(): Result<Movies, DataError.NetworkError>
    suspend fun getMovieSearchResult(query: String): Result<Movies, DataError.NetworkError>
}
