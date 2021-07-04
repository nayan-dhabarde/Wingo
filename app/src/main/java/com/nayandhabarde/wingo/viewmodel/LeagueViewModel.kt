package com.nayandhabarde.wingo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.repository.LeagueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(val repository: LeagueRepository): ViewModel() {

    fun fetchDataFromRepo(refresh: Boolean = false): Flow<PagingData<League>> {
        return repository.fetchLeague()
    }

}