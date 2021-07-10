package com.nayandhabarde.wingo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.retrofit.ApiService
import java.lang.Exception

class TournamentPagingSource(
    private val apiService: ApiService,
    private val pageSize: Int
): PagingSource<Int, Tournament>() {
    override fun getRefreshKey(state: PagingState<Int, Tournament>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tournament> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getTournaments(nextPageNumber, pageSize).await()
            LoadResult.Page(
                data = response.data,
                prevKey = null, // Only one direction paging
                nextKey = nextPageNumber + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(
                ex as Throwable
            )
        }
    }

}