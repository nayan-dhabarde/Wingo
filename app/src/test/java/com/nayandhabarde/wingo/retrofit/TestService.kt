package com.nayandhabarde.wingo.retrofit

import com.nayandhabarde.wingo.model.PageResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PageTestService {

    @GET("/")
    fun getPageResponse(): Deferred<PageResponse<MutableList<String>>>
}