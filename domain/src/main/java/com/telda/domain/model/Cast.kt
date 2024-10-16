package com.telda.domain.model

import com.google.gson.annotations.SerializedName

data class Cast(
    val adult: Boolean,
    @SerializedName("cast_id")
    val castId: Int,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    override val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    override val name: String,
    val order: Int,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    override val profilePath: String
): Contributor()
