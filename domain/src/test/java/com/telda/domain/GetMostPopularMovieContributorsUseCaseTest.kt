package com.telda.domain

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.domain
 */
import com.telda.domain.model.Cast
import com.telda.domain.model.Crew
import com.telda.domain.model.MovieContributors
import com.telda.domain.usecase.GetMostPopularMovieContributorsUseCase
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class GetMostPopularMovieContributorsUseCaseTest {

    private lateinit var useCase: GetMostPopularMovieContributorsUseCase

    @Before
    fun setup() {
        useCase = GetMostPopularMovieContributorsUseCase()
    }

    private fun createFakeCast(
        id: Int,
        name: String,
        knownForDepartment: String,
        popularity: Double
    ): Cast {
        val cast = mockk<Cast>()
        mockk<Cast>().apply {
            every { cast.id } returns id
            every { cast.name } returns name
            every { cast.knownForDepartment } returns knownForDepartment
            every { cast.popularity } returns popularity
        }
        return cast
    }

    private fun createFakeCrew(
        id: Int,
        name: String,
        knownForDepartment: String,
        popularity: Double
    ): Crew {
        val crew = mockk<Crew>()
        mockk<Crew>().apply {
            every { crew.id } returns id
            every { crew.name } returns name
            every { crew.knownForDepartment } returns knownForDepartment
            every { crew.popularity } returns popularity
        }
        return crew
    }

    private fun createFakeMovieContributors(
        cast: List<Cast>,
        crew: List<Crew>
    ): MovieContributors {
        val movieContributors = mockk<MovieContributors>()
        mockk<MovieContributors>().apply {
            every { movieContributors.cast } returns cast
            every { movieContributors.crew } returns crew
        }
        return movieContributors
    }

    @Test
    fun `GIVEN a list of contributors WHEN invoke is called THEN return top 5 actors + directors`() {
        // GIVEN
        val cast = listOf(
            createFakeCast(1, "Actor A", "Acting", 10.0),
            createFakeCast(2, "Actor B", "Acting", 20.0),
            createFakeCast(3, "Actor C", "Acting", 15.0),
            createFakeCast(1, "Actor A", "Acting", 10.0), // Duplicate
            createFakeCast(4, "Actor D", "Acting", 5.0)
        )

        val crew = listOf(
            createFakeCrew(5, "Director A", "Directing", 25.0),
            createFakeCrew(6, "Director B", "Directing", 30.0),
            createFakeCrew(7, "Director C", "Directing", 20.0)
        )

        val contributors = createFakeMovieContributors(cast, crew)

        // WHEN
        val result = useCase(contributors)

        // THEN
        assertEquals(7, result.size)
        assertEquals(2, result[0].id)
        assertEquals(3, result[1].id)
        assertEquals(1, result[2].id)
        assertEquals(4, result[3].id)
        assertEquals(6, result[4].id)
    }

    @Test
    fun `GIVEN contributors with no actors WHEN invoke is called THEN return top 5 directors`() {
        // GIVEN
        val crew = listOf(
            createFakeCrew(5, "Director A", "Directing", 25.0),
            createFakeCrew(6, "Director B", "Directing", 30.0)
        )

        val contributors = createFakeMovieContributors(emptyList(), crew)

        // WHEN
        val result = useCase(contributors)

        // THEN
        assertEquals(2, result.size)
        assertEquals(6, result[0].id)
        assertEquals(5, result[1].id)
    }

    @Test
    fun `GIVEN contributors with no directors WHEN invoke is called THEN return top 5 actors`() {
        // GIVEN
        val cast = listOf(
            createFakeCast(1, "Actor A", "Acting", 10.0),
            createFakeCast(2, "Actor B", "Acting", 20.0)
        )

        val contributors = createFakeMovieContributors(cast, emptyList())

        // WHEN
        val result = useCase(contributors)

        // THEN
        assertEquals(2, result.size)
        assertEquals(2, result[0].id)
        assertEquals(1, result[1].id)
    }

    @Test
    fun `GIVEN contributors with duplicate actors and directors WHEN invoke is called THEN return unique top contributors`() {
        // GIVEN
        val cast = listOf(
            createFakeCast(1, "Actor A", "Acting", 10.0),
            createFakeCast(1, "Actor A", "Acting", 10.0), // Duplicate
            createFakeCast(2, "Actor B", "Acting", 20.0)
        )

        val crew = listOf(
            createFakeCrew(3, "Director A", "Directing", 25.0),
            createFakeCrew(3, "Director A", "Directing", 25.0) // Duplicate
        )

        val contributors = createFakeMovieContributors(cast, crew)

        // WHEN
        val result = useCase(contributors)

        // THEN
        assertEquals(3, result.size) // Unique contributors
        assertEquals(2, result[0].id)
        assertEquals(1, result[1].id)
        assertEquals(3, result[2].id)
    }

    @Test
    fun `GIVEN contributors with not all actors and directors WHEN invoke is called THEN return unique top contributors no other departments`() {
        // GIVEN
        val cast = listOf(
            createFakeCast(1, "Actor A", "Acting", 10.0),
            createFakeCast(2, "Actor B", "Acting", 20.0),
            createFakeCast(3, "Actor B", "whatever", 100.0)
        )

        val crew = listOf(
            createFakeCrew(4, "Director A", "Whatever", 50.0),
            createFakeCrew(5, "Director A", "Directing", 25.0),
        )

        val contributors = createFakeMovieContributors(cast, crew)

        // WHEN
        val result = useCase(contributors)

        // THEN
        assertEquals(3, result.size) // Unique contributors
        assertEquals(2, result[0].id)
        assertEquals(1, result[1].id)
        assertEquals(5, result[2].id)
    }
}
