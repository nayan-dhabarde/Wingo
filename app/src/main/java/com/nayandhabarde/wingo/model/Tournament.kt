package com.nayandhabarde.wingo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Tournament (
    @SerializedName("begin_at")
    val beginAt: String = "",
    @SerializedName("end_at")
    val endAT: String = "",
    val id: Long,
    val league: League = League(),
    @SerializedName("league_id")
    val leagueId: Long = Long.MIN_VALUE,
    val matches: List<Match> = listOf(),
    @SerializedName("modified_at")
    val modifiedAT: String = "",
    val name: String = "",
    val prizepool: String? = null,
    val serie: Serie = Serie(),
    @SerializedName("serie_id")
    val serieId: Long? = null,
    val teams: List<Team> = listOf(),
    @SerializedName("winner_id")
    val winnerId: Long? = null,
    @SerializedName("winner_type")
    val winnerType: String? = null
): Serializable

enum class WinnerType(val value: String) {
    TEAM("team")
}