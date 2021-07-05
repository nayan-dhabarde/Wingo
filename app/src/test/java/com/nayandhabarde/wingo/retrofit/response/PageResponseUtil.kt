package com.nayandhabarde.wingo.retrofit.response

import androidx.paging.PagingData
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.PageResponse
import com.nayandhabarde.wingo.paging.LeagueFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PageResponseUtil {
    private val leagueFactory = LeagueFactory()

    fun getPageOneResponse(): CompletableDeferred<PageResponse<MutableList<League>>> {
        val deferred = CompletableDeferred<PageResponse<MutableList<League>>>()
        val firstResponse = mutableListOf(
            leagueFactory.create(6253),
            leagueFactory.create(6252)
        )
        deferred.complete(PageResponse(firstResponse, 3))
        return deferred
    }

    fun getPageOneResponseFlow(): Flow<PagingData<League>> {
        return flow {
            this.emit(
                PagingData.from(mutableListOf(
                    leagueFactory.create(6253),
                    leagueFactory.create(6252)
                ))
            )
        }
    }
}