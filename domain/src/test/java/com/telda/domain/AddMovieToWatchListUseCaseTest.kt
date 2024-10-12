package com.telda.domain

import com.telda.domain.repository.MoviesRepository
import com.telda.domain.usecase.AddMovieToWatchListUseCase
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
class AddMovieToWatchListUseCaseTest {
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var useCase: AddMovieToWatchListUseCase

    @Before
    fun setup() {
        moviesRepository = mockk(relaxed = true)
        useCase = AddMovieToWatchListUseCase(moviesRepository)
    }

    @Test
    fun `GIVEN a movie ID WHEN invoke is called THEN it calls addMovieToWatchList on MoviesRepository`() = runBlocking {
        // GIVEN
        val movieId = 1

        // WHEN
        useCase(movieId)

        // THEN
        coVerify(exactly = 1) { moviesRepository.addMovieToWatchList(movieId) }
    }
}
