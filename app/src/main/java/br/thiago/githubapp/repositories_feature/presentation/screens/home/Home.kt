package br.thiago.githubapp.repositories_feature.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import br.thiago.githubapp.R
import br.thiago.githubapp.core.utils.delegates.autoNull
import br.thiago.githubapp.core.utils.delegates.viewBinding
import br.thiago.githubapp.core.utils.extensions.collectRepeatOnStart
import br.thiago.githubapp.databinding.LayoutHomeBinding
import br.thiago.githubapp.repositories_feature.presentation.screens.home.adapter.RepositoriesListAdapter
import br.thiago.githubapp.repositories_feature.presentation.screens.home.adapter.RepositoriesListStateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class Home : Fragment(R.layout.layout_home) {
    private val binding by viewBinding(LayoutHomeBinding::bind)
    private val viewModel by viewModel<HomeViewModel>()
    private val listAdapter by autoNull {

        RepositoriesListAdapter { repository ->
            val owner = repository.owner.login
            val repo = repository.name
            findNavController().navigate(
                HomeDirections.actionHomeToPullRequestDetails(owner, repo)
            )
        }
    }


    private val listRefreshStateAdapter by autoNull {
        RepositoriesListStateAdapter { listAdapter.retry() }
    }
    private val listStateAdapter by autoNull {
        RepositoriesListStateAdapter { listAdapter.retry() }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribeUi()
    }

    private fun initView() {
        binding.listView.adapter =
            ConcatAdapter(
                listRefreshStateAdapter,
                listAdapter,
                listStateAdapter,
            )
    }

    private fun subscribeUi() {
        viewModel.repository.collectRepeatOnStart(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                listAdapter.submitData(pagingData)
            }
        }
        listAdapter.loadStateFlow.collectRepeatOnStart(viewLifecycleOwner) { combinedLoadStates ->
            listRefreshStateAdapter.loadState = combinedLoadStates.refresh
            listStateAdapter.loadState = combinedLoadStates.append
        }
    }
}
