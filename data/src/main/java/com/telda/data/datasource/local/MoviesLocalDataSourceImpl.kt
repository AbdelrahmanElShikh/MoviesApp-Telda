package com.telda.data.datasource.local

import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.data.datasource.local
 */
class MoviesLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) : MoviesLocalDataSource {
    override suspend fun getMovies(): List<Int> = movieDao.getMovies().map { it.id }

    override suspend fun addMovie(movieId: Int) = movieDao.addMovie(movie = DbMovie(id = movieId))

    override suspend fun deleteMovie(movieId: Int) = movieDao.deleteMovie(movie = DbMovie(id = movieId))
}
