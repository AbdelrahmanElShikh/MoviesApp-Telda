package com.telda.data.datasource

import com.telda.data.remote.MovieApiService
import com.telda.domain.model.Movies
import retrofit2.Response
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 08-Oct-24
 * @Project : com.telda.data
 */
class MoviesRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService
) : MoviesRemoteDataSource {
    override suspend fun getPopularMovies(): Response<Movies> = apiService.getPopularMovies()

    override suspend fun getMovieSearchResult(query: String): Response<Movies> = apiService.getMovieSearchResult(query = query)
}
