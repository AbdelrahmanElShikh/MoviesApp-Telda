package com.telda.domain.repository

import com.telda.domain.model.MovieContributors
import com.telda.domain.model.MovieDetails
import com.telda.domain.model.Movies
import com.telda.domain.result.Result
import com.telda.domain.result.error.DataError


interface MoviesRepository {
    suspend fun getPopularMovies(): Result<Movies, DataError.NetworkError>
    suspend fun getMovieSearchResult(query: String): Result<Movies, DataError.NetworkError>
    suspend fun getMovieDetailsById(movieId: Int): Result<MovieDetails, DataError.NetworkError>
    suspend fun getSimilarMoviesById(movieId: Int): Result<Movies, DataError.NetworkError>
    suspend fun getMovieContributors(movieId: Int): Result<MovieContributors, DataError.NetworkError>
}
