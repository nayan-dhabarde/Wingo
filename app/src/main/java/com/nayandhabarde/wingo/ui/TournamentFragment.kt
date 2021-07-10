package com.nayandhabarde.wingo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nayandhabarde.wingo.R
import com.nayandhabarde.wingo.databinding.TournamentFragmentBinding
import com.nayandhabarde.wingo.diffcallback.TournamentDiffCallback
import com.nayandhabarde.wingo.ui.adapter.TournamentAdapter
import com.nayandhabarde.wingo.viewmodel.TournamentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TournamentFragment: Fragment(R.layout.tournament_fragment) {
    private var _binding: TournamentFragmentBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var adapter: TournamentAdapter
    private val viewModel: TournamentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TournamentFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
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