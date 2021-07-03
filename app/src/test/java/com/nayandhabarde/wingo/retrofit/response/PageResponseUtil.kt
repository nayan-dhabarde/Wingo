package com.nayandhabarde.wingo.retrofit.response

import androidx.paging.PagingData
import com.nayandhabarde.wingo.model.PageResponse
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.paging.TournamentFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PageResponseUtil {
    private val tournamentFactory = TournamentFactory()

    fun getPageOneResponse(): CompletableDeferred<PageResponse<MutableList<Tournament>>> {
        val deferred = CompletableDeferred<PageResponse<MutableList<Tournament>>>()
        val firstResponse = mutableListOf(
            tournamentFactory.create(6253),
            tournamentFactory.create(6252)
        )
        deferred.complete(PageResponse(firstResponse, 3))
        return deferred
    }

    fun getPageOneResponseFlow(): Flow<PagingData<Tournament>> {
        return flow {
            this.emit(
                PagingData.from(mutableListOf(
                    tournamentFactory.create(6253),
                    tournamentFactory.create(6252)
                ))
            )
        }
    }
}