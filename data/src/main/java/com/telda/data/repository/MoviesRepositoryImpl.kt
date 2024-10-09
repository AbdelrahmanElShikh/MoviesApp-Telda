package com.telda.data.repository

import com.telda.data.datasource.MoviesRemoteDataSource
import com.telda.data.resource.error.responseHandler
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
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun getPopularMovies(): Result<Movies, DataError.NetworkError> = responseHandler {
        remoteDataSource.getPopularMovies()
    }

    override suspend fun getMovieSearchResult(query: String): Result<Movies, DataError.NetworkError> = responseHandler {
        remoteDataSource.getMovieSearchResult(query = query)
    }
}
