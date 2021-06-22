package com.nayandhabarde.wingo.retrofit.response

import com.nayandhabarde.wingo.constants.WingoHeaders
import okhttp3.mockwebserver.MockResponse
import okio.buffer
import okio.source

class MockResponseUtil {
    fun getTournamentsResponse(): MockResponse {
        return getResponseFor("tournaments.json",
            mapOf(WingoHeaders.HEADER_X_TOTAL.value to 1940.toString()))
    }

    private fun getResponseFor(fileName: String, headers: Map<String, String> = emptyMap()): MockResponse {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        return mockResponse
    }
}