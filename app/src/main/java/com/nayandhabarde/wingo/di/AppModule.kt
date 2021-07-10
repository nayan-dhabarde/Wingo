package com.nayandhabarde.wingo.di

import com.nayandhabarde.wingo.constants.WingoDateFormats
import com.nayandhabarde.wingo.diffcallback.TournamentDiffCallback
import com.nayandhabarde.wingo.ui.adapter.TournamentAdapter
import com.nayandhabarde.wingo.util.WingoDateTimeFormatter
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
    @ServerDateFormat
    fun provideServerSingleDateFormat(): SimpleDateFormat {
        return SimpleDateFormat(WingoDateFormats.SERVER_FORMAT.value, Locale.getDefault())
    }

    @Provides
    fun provideTournamentAdapter(diffCallback: TournamentDiffCallback, wingoDateTimeFormatter: WingoDateTimeFormatter): TournamentAdapter {
        return TournamentAdapter(diffCallback, wingoDateTimeFormatter)
    }
}