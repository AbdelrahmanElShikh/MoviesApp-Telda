package com.telda.domain.usecase

import com.telda.domain.model.MovieContributors
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.domain.usecase
 */
class GetMostPopularMovieContributorsUseCase @Inject constructor() {
    operator fun invoke(contributors: MovieContributors) =
        contributors.cast
            .filter { it.knownForDepartment == "Acting" }
            .sortedByDescending { it.popularity }
            .distinctBy { it.id }
            .take(5) + contributors.crew
            .filter { it.knownForDepartment == "Directing" }
            .sortedByDescending { it.popularity }
            .distinctBy { it.id }
            .take(5)
}
