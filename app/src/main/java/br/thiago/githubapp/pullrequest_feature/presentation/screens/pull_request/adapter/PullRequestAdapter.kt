package br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.thiago.githubapp.databinding.PullRequestListItemBinding
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import br.thiago.githubapp.pullrequest_feature.presentation.common.PullRequestViewHolder
import br.thiago.githubapp.core.utils.SimpleDiffCallback
import br.thiago.githubapp.core.utils.delegates.viewBinding

class PullRequestAdapter(
    private val onPullRequestClick: (PullRequest) -> Unit
) : PagingDataAdapter<PullRequest, PullRequestViewHolder>(SimpleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder =
        PullRequestViewHolder(
            binding = parent.viewBinding { layoutInflater, viewGroup, _ ->
                PullRequestListItemBinding.inflate(layoutInflater, viewGroup, false)
            },
            onClick = onPullRequestClick
        )

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}
