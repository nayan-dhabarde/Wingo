package com.nayandhabarde.wingo.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.nayandhabarde.wingo.model.Team

class TeamDiffCallback: DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }
}