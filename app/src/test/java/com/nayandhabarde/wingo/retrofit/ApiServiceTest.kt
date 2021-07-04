package com.nayandhabarde.wingo.retrofit

import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.retrofit.response.MockResponseUtil
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceTest {

    @get:Rule
    val server = MockWebServer()

    lateinit var service: ApiService
    val mockUtils = MockResponseUtil()

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(PageResponseCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        service = retrofit.create(ApiService::class.java)
    }


    @Test
    fun testTournamentResults() = runBlocking {
        server.enqueue(mockUtils.getTournamentsResponse())
        val deferred = service.getLeagues(1, 10)
        val tournaments: List<Tournament> = deferred.await().data

        val firstTournament = tournaments[0]
        assertThat(firstTournament.id).isEqualTo(6253)
        assertThat(firstTournament.league.name).isEqualTo("DreamHack Open")
    }


}