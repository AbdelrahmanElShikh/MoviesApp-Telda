package com.telda.moviesapp.screens

import kotlinx.serialization.Serializable

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.screens
 */
@Serializable
object ScreenMovieSearch

@Serializable
data class ScreenMovieDetails(
    val movieId: Int,
    val movieName: String,
    val inWatchList: Boolean,
)
