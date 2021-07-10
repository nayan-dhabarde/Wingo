package com.nayandhabarde.wingo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.nayandhabarde.wingo.databinding.LeagueViewBinding
import com.nayandhabarde.wingo.model.League
import com.nayandhabarde.wingo.ui.viewholder.LeagueViewHolder

class LeagueAdapter(diffCallback: DiffUtil.ItemCallback<League>)
    : PagingDataAdapter<League, LeagueViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val model = getItem(position)
        holder.itemBinding.apply {
            if(model != null) {
                leagueNameTextView.text = model.name
                leagueDateTextView.text = "Jun 29th - Jul 3rd"
                leagueImageView.load(model.imageUrl)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val binding = LeagueViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeagueViewHolder(binding)
    }
}