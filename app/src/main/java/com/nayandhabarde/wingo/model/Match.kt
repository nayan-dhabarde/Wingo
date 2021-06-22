package com.nayandhabarde.wingo.model

import com.google.gson.annotations.SerializedName

data class Match (
    @SerializedName("begin_at")
    val beginAt: String = "",
    @SerializedName("detailed_stats")
    val detailedStats: Boolean = false,
    val draw: Boolean = false,
    @SerializedName("end_at")
    val endAt: String? = null,
    val gameAdvantage: String? = null,
    val id: Long = Long.MIN_VALUE,
    val live: Live = Live(),
    @SerializedName("live_embed_url")
    val liveEmbedUrl: String? = null,
    @SerializedName("match_type")
    val matchType: MatchType = MatchType.BEST_OF,
    @SerializedName("modified_at")
    val modifiedAt: String? = null,
    val name: String? = null,
    @SerializedName("number_of_games")
    val numberOfGames: Int = 0,
    @SerializedName("official_stream_url")
    val officialStreamUrl: String? = null,
    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String? = null,
    val rescheduled: Boolean = false,
    @SerializedName("scheduled_at")
    val scheduledAt: String? = null,
    val slug: String? = null,
    val status: String? = null,
    val streams: Map<String, Stream>? = null,
    val streams_list: List<Stream> = listOf(),
    @SerializedName("tournament_id")
    val tournamentId: Long = Long.MIN_VALUE,
    @SerializedName("winner_id")
    val winnerId: Long? = null
)

enum class MatchType(val value: String) {
    BEST_OF("best_of")
}

enum class StreamType(val value: String) {
    ENGLISH("english"),
    OFFICIAL("official"),
    RUSSIAN("russian")
}