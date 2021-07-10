package com.nayandhabarde.wingo.retrofit

import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.PageResponse
import com.nayandhabarde.wingo.model.Tournament
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/csgo/leagues")
    fun getLeagues(
        @Query("page[number]") page: Int,
        @Query("page[size]") pageSize: Int,
        @Query("sort") sort: String = "modified_at"): Deferred<PageResponse<MutableList<League>>>
}