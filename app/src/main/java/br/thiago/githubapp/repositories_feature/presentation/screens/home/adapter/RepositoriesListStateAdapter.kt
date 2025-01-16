package br.thiago.githubapp.repositories_feature.presentation.screens.home.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.thiago.githubapp.core.utils.delegates.viewBinding
import br.thiago.githubapp.databinding.RepositoryStateListItemBinding


class RepositoriesListStateAdapter(
    private val retryListener: () -> Unit,
) : LoadStateAdapter<RepositoriesListStateAdapter.RepositoriesListStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): RepositoriesListStateViewHolder =
        RepositoriesListStateViewHolder(
            parent.viewBinding { layoutInflater, viewGroup, _ ->
                RepositoryStateListItemBinding.inflate(layoutInflater, viewGroup, false)
            },
        )

    override fun onBindViewHolder(
        holder: RepositoriesListStateViewHolder,
        loadState: LoadState,
    ) {
        holder.bind(loadState)
    }

    inner class RepositoriesListStateViewHolder(
        private val binding: RepositoryStateListItemBinding,
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
