package com.telda.domain.usecase

import com.telda.domain.model.MovieOverview
import com.telda.domain.repository.MoviesRepository
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.domain.usecase
 */
class CombineInWatchListToMovieUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(movies: List<MovieOverview>): List<MovieOverview> {
        val moviesInWatchListIds = moviesRepository.getInWatchListMovieIds()
        movies.map { movie ->
            movie.inWatchList = moviesInWatchListIds.contains(movie.id)
        }
        return movies
    }
}
