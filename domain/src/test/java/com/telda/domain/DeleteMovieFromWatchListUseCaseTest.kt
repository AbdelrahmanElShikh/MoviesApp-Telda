package com.telda.domain

import com.telda.domain.repository.MoviesRepository
import com.telda.domain.usecase.DeleteMovieFromWatchListUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 12-Oct-24
 * @Project : com.telda.domain
 */
class DeleteMovieFromWatchListUseCaseTest {
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var useCase: DeleteMovieFromWatchListUseCase

    @Before
    fun setup() {
        moviesRepository = mockk(relaxed = true)
        useCase = DeleteMovieFromWatchListUseCase(moviesRepository)
    }

    @Test
    fun `GIVEN a movie ID WHEN invoke is called THEN it calls deleteMovieFromWatchList on MoviesRepository`() = runBlocking {
        // GIVEN
        val movieId = 1

        // WHEN
        useCase(movieId)

        // THEN
        coVerify(exactly = 1) { moviesRepository.deleteMovieFromWatchList(movieId) }
    }
}
