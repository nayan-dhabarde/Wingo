package com.nayandhabarde.wingo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nayandhabarde.wingo.constants.PageSize
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.paging.TournamentPagingSource
import com.nayandhabarde.wingo.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TournamentRepository @Inject constructor(val apiService: ApiService) {

    fun fetchLeague(): Flow<PagingData<Tournament>> {
        val pageSize = PageSize.TOURNAMENTS.value
        return Pager(
            PagingConfig(pageSize)
        ) {
            TournamentPagingSource(apiService, pageSize)
        }.flow
    }

}