package com.nayandhabarde.wingo.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.model.Tournament

class LeagueDiffCallback: DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
        return oldItem == newItem
    }
}