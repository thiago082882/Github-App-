package br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.thiago.githubapp.core.utils.delegates.viewBinding
import br.thiago.githubapp.databinding.PullRequestStateListItemBinding


class PullRequestListStateAdapter(
    private val retryListener: () -> Unit,
) : LoadStateAdapter<PullRequestListStateAdapter.PullRequestListStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): PullRequestListStateViewHolder =
        PullRequestListStateViewHolder(
            parent.viewBinding { layoutInflater, viewGroup, _ ->
               PullRequestStateListItemBinding.inflate(layoutInflater, viewGroup, false)
            },
        )

    override fun onBindViewHolder(
        holder: PullRequestListStateViewHolder,
        loadState: LoadState,
    ) {
        holder.bind(loadState)
    }

    inner class PullRequestListStateViewHolder(
        private val binding:   PullRequestStateListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retryListener() }
        }

        fun bind(loadState: LoadState) {
            binding.loadingIndicator.isVisible = loadState is LoadState.Loading
            binding.error.isVisible = loadState is LoadState.Error
        }
    }
}
