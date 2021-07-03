package com.nayandhabarde.wingo.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.nayandhabarde.wingo.model.Tournament

class TournamentDiffCallback: DiffUtil.ItemCallback<Tournament>() {
    override fun areItemsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
        return oldItem == newItem
    }
}