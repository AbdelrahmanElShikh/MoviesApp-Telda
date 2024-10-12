package com.telda.domain

import com.telda.domain.model.MovieOverview
import com.telda.domain.repository.MoviesRepository
import com.telda.domain.usecase.CombineInWatchListToMovieUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.domain
 */
class CombineInWatchListToMovieUseCaseTest {

    private lateinit var moviesRepository: MoviesRepository
    private lateinit var useCase: CombineInWatchListToMovieUseCase

    @Before
    fun setup() {
        moviesRepository = mockk()
        useCase = CombineInWatchListToMovieUseCase(moviesRepository)
    }

    @Test
    fun `GIVEN movies and a watchlist WHEN invoke is called THEN movies are marked correctly`(): Unit = runBlocking {
        // GIVEN
        val movie1 = MovieOverview(
            adult = false,
            id = 1,
            overview = "",
            releaseDate = "",
            posterPath = "",
            title = "",
        )
        val movie2 = MovieOverview(
            adult = false,
            id = 2,
            overview = "",
            releaseDate = "",
            posterPath = "",
            title = "",
        )
        val movies = listOf(movie1, movie2)
        val watchListIds = listOf(1) // Movie with id 1 is in watchlist

        coEvery { moviesRepository.getInWatchListMovieIds() } returns watchListIds

        // WHEN
        val result = useCase(movies)

        // THEN
        assert(result[0].inWatchList) // movie1 should be in watchlist
        assert(result[1].inWatchList.not()) // movie2 should not be in watchlist
    }

    @Test
    fun `GIVEN movies and no watchlist WHEN invoke is called THEN no movies are marked as in watchlist`() = runBlocking {
        // GIVEN
        val movie1 = MovieOverview(
            adult = false,
            id = 1,
            overview = "",
            releaseDate = "",
            posterPath = "",
            title = "",
        )
        val movie2 = MovieOverview(
            adult = false,
            id = 2,
            overview = "",
            releaseDate = "",
            posterPath = "",
            title = "",
        )
        val movies = listOf(movie1, movie2)
        val watchListIds = emptyList<Int>() // No movies in watchlist

        coEvery { moviesRepository.getInWatchListMovieIds() } returns watchListIds

        // WHEN
        val result = useCase(movies)

        // THEN
        assert(result[0].inWatchList.not()) // movie1 should not be in watchlist
        assert(result[1].inWatchList.not()) // movie2 should not be in watchlist
    }
}
