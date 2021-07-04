package com.nayandhabarde.wingo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nayandhabarde.wingo.BuildConfig
import com.nayandhabarde.wingo.retrofit.ApiService
import com.nayandhabarde.wingo.retrofit.PageResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.nayandhabarde.wingo.retrofit.AuthInterceptor


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(PageResponseCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideOkHttp(authInterceptor: AuthInterceptor): OkHttpClient {
        val builder =  OkHttpClient.Builder()
        builder.addInterceptor(authInterceptor)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        return builder.build()
    }

    @Provides
    fun provideOkhttpInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}