package com.nayandhabarde.wingo.di

import com.nayandhabarde.wingo.constants.WingoDateFormats
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideServerSingleDateFormat(): SimpleDateFormat {
        return SimpleDateFormat(WingoDateFormats.SERVER_FORMAT.value, Locale.getDefault())
    }
}