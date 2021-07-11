package com.nayandhabarde.wingo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nayandhabarde.wingo.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(private val repository: TournamentRepository): ViewModel() {

    val tournaments = repository.fetchTournaments().cachedIn(viewModelScope)


}