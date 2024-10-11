package com.telda.domain.model

data class MovieContributors(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

abstract class Contributor {
    abstract val id: Int
    abstract val name: String
    abstract val profilePath: String
}
