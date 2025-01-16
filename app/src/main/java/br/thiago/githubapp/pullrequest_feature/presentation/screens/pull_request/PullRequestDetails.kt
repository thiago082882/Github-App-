package br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import br.thiago.githubapp.R
import br.thiago.githubapp.core.utils.UiState
import br.thiago.githubapp.core.utils.delegates.autoNull
import br.thiago.githubapp.core.utils.delegates.viewBinding
import br.thiago.githubapp.core.utils.extensions.collectRepeatOnStart
import br.thiago.githubapp.databinding.LayoutPullRequestBinding
import br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request.adapter.PullRequestAdapter
import br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request.adapter.PullRequestListStateAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PullRequestDetails : Fragment(R.layout.layout_pull_request) {
    private val binding by viewBinding(LayoutPullRequestBinding::bind)

    private val args by navArgs<PullRequestDetailsArgs>()

    private val viewModel by viewModel<PullRequestViewModel> {
        parametersOf(args.owner, args.repo)
    }
    private val listAdapter by autoNull {
        PullRequestAdapter {
            findNavController().navigate(PullRequestDetailsDirections.actionPullRequestDetailsToHome())
        }
    }

    private val listRefreshStateAdapter by autoNull {
        PullRequestListStateAdapter { listAdapter.retry() }
    }

    private val listStateAdapter by autoNull {
        PullRequestListStateAdapter { listAdapter.retry() }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)


        binding.toolbar.title = args.repo

        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }


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
        viewModel.fetchPullRequests(args.owner, args.repo)

        viewModel.uiState.collectRepeatOnStart(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.listView.isVisible = false
                    binding.progressBar.isVisible = true
                    binding.errorTextView.isVisible = false
                }

                is UiState.Error -> {
                    binding.listView.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.errorTextView.isVisible = true

                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(getString(R.string.error_title))
                        .setMessage(getString(R.string.error_message))
                        .setPositiveButton(getString(R.string.retry_button)) { _, _ ->
                            viewModel.fetchPullRequests(args.owner, args.repo)
                        }
                        .setNegativeButton(getString(R.string.back_button)) { _, _ ->
                            findNavController().navigateUp()
                        }
                        .show()
                }

                is UiState.Result -> {
                    binding.listView.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.errorTextView.isVisible = false
                    viewLifecycleOwner.lifecycleScope.launch {
                        listAdapter.submitData(state.item)
                    }
                }
            }

            listAdapter.loadStateFlow.collectRepeatOnStart(viewLifecycleOwner) { combinedLoadStates ->
                listRefreshStateAdapter.loadState = combinedLoadStates.refresh
                listStateAdapter.loadState = combinedLoadStates.append
            }
        }
    }
}




