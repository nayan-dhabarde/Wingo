package com.nayandhabarde.wingo.model

import com.google.gson.annotations.SerializedName

data class Serie (

    @SerializedName("begin_at")
    val beginAt: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("end_at")
    val endAt:String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("league_id")
    val leagueId: Long? = null,
    @SerializedName("modified_at")
    val modifiedAt: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("season")
    val season: String? =  null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("tier")
    val tier: String?= null,
    @SerializedName("winner_id")
    val winnerId: String?= null,
    @SerializedName("winner_type")
    val winnerType: String?= null,
    @SerializedName("year")
    val year: Int? = null
)