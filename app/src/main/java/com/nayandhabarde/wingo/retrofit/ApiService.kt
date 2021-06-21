package com.nayandhabarde.wingo.retrofit

import com.nayandhabarde.wingo.model.PageResponse
import com.nayandhabarde.wingo.model.Tournament
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ApiService {

    @GET("/csgo/tournaments")
    fun getTournaments(): Deferred<PageResponse<MutableList<Tournament>>>
}