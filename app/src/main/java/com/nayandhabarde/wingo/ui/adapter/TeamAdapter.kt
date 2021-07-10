package com.nayandhabarde.wingo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.nayandhabarde.wingo.databinding.TeamViewBinding
import com.nayandhabarde.wingo.diffcallback.TeamDiffCallback
import com.nayandhabarde.wingo.model.Team
import com.nayandhabarde.wingo.ui.viewholder.TeamViewHolder

class TeamAdapter: ListAdapter<Team, TeamViewHolder>(TeamDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val model = getItem(position)
        if(model != null)  {
            holder.itemBinding.apply {
                teamImageView.load(model.imageUrl)
            }
        }
    }
}