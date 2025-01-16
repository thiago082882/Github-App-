package br.thiago.githubapp.repositories_feature.presentation.screens.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.thiago.githubapp.databinding.RepositoryListItemBinding
import br.thiago.githubapp.repositories_feature.domain.model.Repository
import br.thiago.githubapp.repositories_feature.presentation.common.RepositoryViewHolder
import br.thiago.githubapp.core.utils.SimpleDiffCallback
import br.thiago.githubapp.core.utils.delegates.viewBinding

class RepositoriesListAdapter(
    private val onRepositoryClick: (Repository) -> Unit,
) : PagingDataAdapter<Repository, RepositoryViewHolder>(SimpleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
        RepositoryViewHolder(
            binding = parent.viewBinding { layoutInflater, viewGroup, _ ->
                RepositoryListItemBinding.inflate(layoutInflater, viewGroup, false)
            },
            onClick = onRepositoryClick
        )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}
