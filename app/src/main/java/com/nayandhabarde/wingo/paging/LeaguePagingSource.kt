package com.nayandhabarde.wingo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.retrofit.ApiService
import java.lang.Exception

class LeaguePagingSource(
    private val apiService: ApiService,
    private val pageSize: Int
): PagingSource<Int, League>() {
    override fun getRefreshKey(state: PagingState<Int, League>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, League> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getLeagues(nextPageNumber, pageSize).await()
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