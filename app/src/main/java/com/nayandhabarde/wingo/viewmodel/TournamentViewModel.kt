package com.nayandhabarde.wingo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(val repository: TournamentRepository): ViewModel() {

    fun fetchDataFromRepo(refresh: Boolean = false): Flow<PagingData<Tournament>> {
        return repository.fetchLeague()
    }

}