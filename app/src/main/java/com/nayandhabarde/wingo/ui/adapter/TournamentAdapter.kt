package com.nayandhabarde.wingo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.nayandhabarde.wingo.databinding.TournamentViewBinding
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.ui.viewholder.TournamentViewHolder
import com.nayandhabarde.wingo.util.WingoDateTimeFormatter
import javax.inject.Inject

class TournamentAdapter @Inject constructor(
    diffCallback: DiffUtil.ItemCallback<Tournament>,
    private val wingoDateTimeFormatter: WingoDateTimeFormatter
)
    : PagingDataAdapter<Tournament, TournamentViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val model = getItem(position)
        holder.itemBinding.apply {
            if(model != null) {
                tournamentNameTextView.text = "${model.league.name} ${model.name}"
                tournamentDateTextView.text = wingoDateTimeFormatter.transformStartAndEndDateToMonthDateOfMonthRange(model.beginAt, model.endAT)
                tournamentImageView.load(model.league.imageUrl)

                teamsRecyclerView.layoutManager = LinearLayoutManager(teamsRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                val adapter = TeamAdapter()
                teamsRecyclerView.adapter = adapter
                adapter.submitList(model.teams)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val binding = TournamentViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TournamentViewHolder(binding)
    }
}