package com.telda.data.datasource.local

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.data.datasource.local
 */
interface MoviesLocalDataSource {

    suspend fun getMovies(): List<Int>

    suspend fun addMovie(movieId: Int)

    suspend fun deleteMovie(movieId: Int)
}
