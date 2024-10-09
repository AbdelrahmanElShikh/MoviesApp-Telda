package com.telda.data.datasource

import com.telda.domain.model.Movies
import retrofit2.Response

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 08-Oct-24
 * @Project : com.telda.data.datasource
 */
interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(): Response<Movies>
    suspend fun getMovieSearchResult(query: String): Response<Movies>
}
