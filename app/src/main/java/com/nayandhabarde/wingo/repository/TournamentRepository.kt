package com.nayandhabarde.wingo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nayandhabarde.wingo.constants.PageSize
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.paging.TournamentPagingSource
import com.nayandhabarde.wingo.retrofit.ApiService
import com.nayandhabarde.wingo.util.WingoDateTimeFormatter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val apiService: ApiService,
    private val wingoDateTimeFormatter: WingoDateTimeFormatter) {

    fun fetchTournaments(): Flow<PagingData<Tournament>> {
        val pageSize = PageSize.TOURNAMENTS.value
        return Pager(
            PagingConfig(pageSize)
        ) {
            TournamentPagingSource(apiService, pageSize,
                wingoDateTimeFormatter.getCurrentMonthDateServerFormatted(),
                wingoDateTimeFormatter.getCurrentMonthDatePlusNextYearServerFormatted()
                )
        }.flow
    }

}