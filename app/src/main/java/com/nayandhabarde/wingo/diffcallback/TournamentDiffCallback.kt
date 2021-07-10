package com.nayandhabarde.wingo.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.Tournament
import javax.inject.Inject

class TournamentDiffCallback @Inject constructor(): DiffUtil.ItemCallback<Tournament>() {
    override fun areItemsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
        return oldItem == newItem
    }
}