package com.nayandhabarde.wingo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nayandhabarde.wingo.R
import com.nayandhabarde.wingo.databinding.LeagueFragmentBinding
import com.nayandhabarde.wingo.diffcallback.LeagueDiffCallback
import com.nayandhabarde.wingo.ui.adapter.LeagueAdapter
import com.nayandhabarde.wingo.viewmodel.LeagueViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LeagueFragment: Fragment(R.layout.league_fragment) {
    private var _binding: LeagueFragmentBinding? = null
    private val binding get() = _binding

    private val viewModel: LeagueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LeagueFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val adapter = LeagueAdapter(LeagueDiffCallback())
            tournamentRecyclerView.layoutManager = LinearLayoutManager(context)
            tournamentRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            tournamentRecyclerView.adapter = adapter
            lifecycleScope.launch {
                viewModel.fetchDataFromRepo().collectLatest {
                    adapter.submitData(it)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}