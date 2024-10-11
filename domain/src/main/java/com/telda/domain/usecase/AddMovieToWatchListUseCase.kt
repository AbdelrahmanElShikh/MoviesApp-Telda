package com.telda.domain.usecase

import com.telda.domain.repository.MoviesRepository
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.domain.usecase
 */
class AddMovieToWatchListUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movieId: Int) = moviesRepository.addMovieToWatchList(movieId = movieId)
}
