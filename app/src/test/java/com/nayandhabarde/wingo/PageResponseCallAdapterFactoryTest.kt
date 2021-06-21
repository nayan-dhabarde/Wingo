package com.nayandhabarde.wingo

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nayandhabarde.wingo.constants.WingoHeaders
import com.nayandhabarde.wingo.model.PageResponse
import com.nayandhabarde.wingo.retrofit.PageResponseCallAdapterFactory
import com.nayandhabarde.wingo.retrofit.PageTestService
import com.nayandhabarde.wingo.retrofit.StringConverterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.exp

class PageResponseCallAdapterFactoryTest {

    @get:Rule
    val server = MockWebServer()
    val callAdapter = PageResponseCallAdapterFactory()
    val gson = Gson()
    lateinit var retrofit: Retrofit
    lateinit var service: PageTestService


    @Before
    fun setup() {
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(callAdapter)
            .build()
        service = retrofit.create(PageTestService::class.java)
    }

    @Test fun returnsNullForOtherType() {
        val type = object : TypeToken<Deferred<String>>() {}.type
        val result = callAdapter.get(type, arrayOf(), retrofit)
        assertThat(result).isEqualTo(null)
    }

    @Test fun returnsCorrect() {
        val type = object : TypeToken<Deferred<Response<String>>>() {}.type
        val result = callAdapter.get(type, arrayOf(), retrofit)
        assertThat(result?.responseType()).isEqualTo(null)
    }

    @Test fun returnsCorrectDeferred() = runBlocking {
        val data = mutableListOf("1", "2", "3")
        val expectedResult = PageResponse(data, 100)
        val json = gson.toJson(data)
       server.enqueue(MockResponse()
            .setBody(json)
            .setHeader(WingoHeaders.HEADER_X_TOTAL.value, 100)
        )
        val deferred = service.getPageResponse()
        val output = deferred.await()
        assertThat(output).isEqualTo(expectedResult)
    }

}