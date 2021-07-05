package com.nayandhabarde.wingo.viewmodel

import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.ListUpdateCallback
import com.google.common.truth.Truth
import com.nayandhabarde.wingo.diffcallback.LeagueDiffCallback
import com.nayandhabarde.wingo.paging.LeagueFactory
import com.nayandhabarde.wingo.repository.LeagueRepository
import com.nayandhabarde.wingo.retrofit.response.PageResponseUtil
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class HomeViewModelTest {

    private val repository = mock(LeagueRepository::class.java)
    private val pageResponseUtil = PageResponseUtil()
    private val tournamentFactory = LeagueFactory()
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Test
    fun fetchDataFromRepoReturnsCorrectFlow() = runBlockingTest(coroutineDispatcher) {
        `when`(repository.fetchLeague()).thenReturn(pageResponseUtil.getPageOneResponseFlow())
        // A little hack as we cannot get data directly from the pagingData
        val differ = AsyncPagingDataDiffer(
            LeagueDiffCallback(),
            noopListUpdateCallback,
            coroutineDispatcher,
            coroutineDispatcher
        )
        val job  = launch {
            repository.fetchLeague().collectLatest {
                differ.submitData(it)
            }
        }

        val actual = differ.snapshot()
        Truth.assertThat(actual).containsExactly(
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