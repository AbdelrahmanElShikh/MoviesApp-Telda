package com.telda.domain.model

import com.google.gson.annotations.SerializedName

data class Crew(
    val adult: Boolean,
    @SerializedName("credit_id") val creditId: String,
    val department: String,
    val gender: Int,
    override val id: Int,
    val job: String,
    @SerializedName("known_for_department") val knownForDepartment: String,
    override val name: String,
    @SerializedName("original_name") val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path") override val profilePath: String
) : Contributor()
