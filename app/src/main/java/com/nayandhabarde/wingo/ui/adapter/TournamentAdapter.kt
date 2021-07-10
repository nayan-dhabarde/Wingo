package com.nayandhabarde.wingo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.nayandhabarde.wingo.databinding.TournamentViewBinding
import com.nayandhabarde.wingo.model.Tournament
import com.nayandhabarde.wingo.ui.viewholder.TournamentViewHolder

class TournamentAdapter(diffCallback: DiffUtil.ItemCallback<Tournament>)
    : PagingDataAdapter<Tournament, TournamentViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val model = getItem(position)
        holder.itemBinding.apply {
            if(model != null) {
                tournamentNameTextView.text = model.name
                tournamentDateTextView.text = "Jun 29th - Jul 3rd"
                tournamentImageView.load(model.league.imageUrl)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val binding = TournamentViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TournamentViewHolder(binding)
    }
}