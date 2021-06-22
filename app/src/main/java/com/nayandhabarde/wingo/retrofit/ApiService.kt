package com.nayandhabarde.wingo.retrofit

import com.nayandhabarde.wingo.model.PageResponse
import com.nayandhabarde.wingo.model.Tournament
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/csgo/tournaments")
    fun getTournaments(
        @Query("page[number]") page: Int,
        @Query("page[size]") pageSize: Int): Deferred<PageResponse<MutableList<Tournament>>>
}