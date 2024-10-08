package com.telda.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 08-Oct-24
 * @Project : com.telda.data.remote
 */
interface MovieApiService {
    @GET("movies/get-popular-movies")
    fun getPopularMovies()

    @GET("search/movie")
    fun getMovieSearchResult(@Query("query") query: String)
}
