package com.telda.data.repository

import com.telda.data.datasource.local.MoviesLocalDataSource
import com.telda.data.datasource.remote.MoviesRemoteDataSource
import com.telda.data.resource.responseHandler
import com.telda.domain.model.MovieContributors
import com.telda.domain.model.MovieDetails
import com.telda.domain.model.Movies
import com.telda.domain.repository.MoviesRepository
import com.telda.domain.result.Result
import com.telda.domain.result.error.DataError
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 08-Oct-24
 * @Project : com.telda.data.repository
 */
class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource,
) : MoviesRepository {
    override suspend fun getPopularMovies(): Result<Movies, DataError.NetworkError> = responseHandler {
        remoteDataSource.getPopularMovies()
    }

    override suspend fun getMovieSearchResult(query: String): Result<Movies, DataError.NetworkError> = responseHandler {
        remoteDataSource.getMovieSearchResult(query = query)
    }

    override suspend fun getMovieDetailsById(movieId: Int): Result<MovieDetails, DataError.NetworkError> = responseHandler {
        remoteDataSource.getMovieDetailsById(movieId = movieId)
    }

    override suspend fun getSimilarMoviesById(movieId: Int): Result<Movies, DataError.NetworkError> = responseHandler {
        remoteDataSource.getSimilarMoviesById(movieId = movieId)
    }

    override suspend fun getMovieContributors(movieId: Int): Result<MovieContributors, DataError.NetworkError> = responseHandler {
        remoteDataSource.getMovieContributors(movieId = movieId)
    }

    override suspend fun getInWatchListMovieIds(): List<Int> = localDataSource.getMovies()

    override suspend fun addMovieToWatchList(movieId: Int) = localDataSource.addMovie(movieId = movieId)

    override suspend fun deleteMovieFromWatchList(movieId: Int) = localDataSource.deleteMovie(movieId = movieId)
}
