package com.nayandhabarde.wingo.retrofit

import retrofit2.http.GET


interface ApiService {

    @GET
    fun getTournaments()
}