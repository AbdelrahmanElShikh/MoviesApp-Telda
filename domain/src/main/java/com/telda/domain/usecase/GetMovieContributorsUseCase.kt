package com.telda.domain.usecase

import com.telda.domain.repository.MoviesRepository
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.domain.usecase
 */
class GetMovieContributorsUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movieId: Int) = moviesRepository.getMovieContributors(movieId = movieId)
}
