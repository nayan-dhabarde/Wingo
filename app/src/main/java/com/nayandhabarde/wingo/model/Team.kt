package com.nayandhabarde.wingo.model

import com.google.gson.annotations.SerializedName

data class Team (
    val acronym: String? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("modified_at")
    val modifiedAt: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null
)