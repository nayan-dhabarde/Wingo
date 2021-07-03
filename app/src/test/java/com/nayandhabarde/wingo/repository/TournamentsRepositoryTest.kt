package com.nayandhabarde.wingo.repository

import androidx.paging.*
import androidx.recyclerview.widget.ListUpdateCallback
import com.google.common.truth.Truth.assertThat
import com.nayandhabarde.wingo.constants.PageSize
import com.nayandhabarde.wingo.diffcallback.TournamentDiffCallback
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.paging.TournamentFactory
import com.nayandhabarde.wingo.retrofit.ApiService
import com.nayandhabarde.wingo.retrofit.response.PageResponseUtil
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TournamentsRepositoryTest {

    private val service = mock(ApiService::class.java)
    private val repository = TournamentsRepository(service)
    private val pageResponseUtil = PageResponseUtil()
    private val tournamentFactory = TournamentFactory()
    private val coroutineDispatcher = TestCoroutineDispatcher()

    // Referrred from Google architecture components sample for Cheese pagination
    @Test
    fun fetchTournamentsReturnsCorrectFlow() = runBlockingTest(coroutineDispatcher) {
        `when`(service.getTournaments(1, PageSize.TOURNAMENTS.value)).thenReturn(pageResponseUtil.getPageOneResponse())
        // A little hack as we cannot get data directly from the pagingData
        val differ = AsyncPagingDataDiffer(
            TournamentDiffCallback(),
            noopListUpdateCallback,
            coroutineDispatcher,
            coroutineDispatcher
        )
        val job  = launch {
            repository.fetchTournaments().collectLatest {
                differ.submitData(it)
            }
        }


        advanceUntilIdle()

        val actual = differ.snapshot()
        assertThat(actual).containsExactly(
            tournamentFactory.create(6253),
            tournamentFactory.create(6252)
        )

        job.cancel()
    }

    val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}