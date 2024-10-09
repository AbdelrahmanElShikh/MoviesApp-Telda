package com.telda.data.remote

import com.telda.domain.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 08-Oct-24
 * @Project : com.telda.data.remote
 */
interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<Movies>

    @GET("search/movie")
    suspend fun getMovieSearchResult(@Query("query") query: String): Response<Movies>
}
