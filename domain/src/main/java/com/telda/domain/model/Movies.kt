package com.telda.domain.model

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    val results: List<MovieOverview>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
