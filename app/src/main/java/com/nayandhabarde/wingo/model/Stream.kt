package com.nayandhabarde.wingo.model

import com.google.gson.annotations.SerializedName

data class Stream (
    @SerializedName("embed_url")
    val embedUrl: String? = null,
    @SerializedName("raw_url")
    val rawUrl: String? = null,
    val language: Language = Language.EN,
    val main: Boolean = false,
    val official: Boolean = false
)

enum class Language(val value: String) {
    EN("en"),
    RU("ru")
}