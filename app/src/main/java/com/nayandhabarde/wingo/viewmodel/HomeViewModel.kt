package com.nayandhabarde.wingo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.repository.TournamentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: TournamentsRepository): ViewModel() {

    fun fetchDataFromRepo(refresh: Boolean): Flow<PagingData<Tournament>> {
        return repository.fetchTournaments()
    }

}