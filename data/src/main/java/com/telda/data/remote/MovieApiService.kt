package com.telda.data.remote

import com.telda.domain.model.MovieContributors
import com.telda.domain.model.MovieDetails
import com.telda.domain.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(@Path("movie_id") movieId: Int): Response<MovieDetails>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMoviesById(@Path("movie_id") movieId: Int): Response<Movies>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieContributors(@Path("movie_id") movieId: Int): Response<MovieContributors>
}
