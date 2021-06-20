package com.nayandhabarde.wingo.retrofit

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import junit.framework.Assert.fail
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.IOException

/**
 * Referred from CoroutinesCallAdapterFactory Github project
 * Good practice for Mockwebserver
 */
class DeferredTest {

    @get:Rule val server =  MockWebServer()
    private lateinit var service: TestService

    interface TestService {
        @GET("/") fun body(): Deferred<String>
        @GET("/") fun response(): Deferred<Response<String>>
    }

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(StringConverterFactory())
            .addCallAdapterFactory(PageResponseCallAdapterFactory())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        service = retrofit.create(TestService::class.java)
    }

    @Test fun bodySuccess200()  = runBlocking {
        server.enqueue(MockResponse().setBody("Hi"))
        val deferred = service.body()
        val result = deferred.await()
        assertThat(result).isEqualTo("Hi")
    }

    @Test fun bodySuccess404(): Unit = runBlocking {
        server.enqueue(MockResponse().setResponseCode(404))
        val deferred = service.body()
        try {
            deferred.await()
        } catch (ex: HttpException) {
            assertThat(ex).hasMessageThat().isEqualTo("HTTP 404 Client Error")
        }
    }

    @Test fun bodyFailure(): Unit = runBlocking {
        server.enqueue(MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AFTER_REQUEST))
        val deferred = service.body()
        try {
            deferred.await()
            fail()
        } catch (ex: IOException) {

        }
    }

    @Test fun responseSuccess200() = runBlocking {
        server.enqueue(MockResponse().setBody("Hi"))

        val deferred = service.response()
        val response = deferred.await()
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isEqualTo("Hi")
    }

    @Test fun responseFailure() = runBlocking {
        server.enqueue(MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AFTER_REQUEST))

        val deferred = service.response()
        try {
            deferred.await()
            fail()
        } catch (ex: IOException) {

        }
    }
}