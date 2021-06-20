package com.nayandhabarde.wingo.retrofit

import com.nayandhabarde.wingo.BuildConfig
import com.nayandhabarde.wingo.constants.WingoHeaders
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader(WingoHeaders.HEADER_AUTHORIZATION.value, BuildConfig.AUTH_TOKEN)
            .build()
        return chain.proceed(modifiedRequest)
    }
}