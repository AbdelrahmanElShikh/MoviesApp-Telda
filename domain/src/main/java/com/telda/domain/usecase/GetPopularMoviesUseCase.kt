package com.telda.domain.usecase

import com.telda.domain.repository.MoviesRepository
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.domain.usecase
 */
class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.getPopularMovies()
}
