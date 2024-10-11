package com.telda.domain.model

import com.google.gson.annotations.SerializedName

abstract class Movie {
    abstract val adult: Boolean
    abstract val id: Int
    abstract val overview: String
    abstract val posterPath: String
    abstract val releaseDate: String
    abstract val title: String
    abstract var inWatchList: Boolean
}

data class MovieOverview(
    override val adult: Boolean,
    override val id: Int,
    override val overview: String,
    @SerializedName("poster_path") override val posterPath: String,
    @SerializedName("release_date") override val releaseDate: String,
    override val title: String,
    override var inWatchList: Boolean = false,
) : Movie()

data class MovieDetails(
    override val adult: Boolean,
    override val id: Int,
    override val overview: String,
    @SerializedName("poster_path") override val posterPath: String,
    @SerializedName("release_date") override val releaseDate: String,
    override val title: String,
    val tagline: String,
    val revenue: Long,
    @SerializedName("backdrop_path") val backdropPath: String,
    override var inWatchList: Boolean = false,
) : Movie()
