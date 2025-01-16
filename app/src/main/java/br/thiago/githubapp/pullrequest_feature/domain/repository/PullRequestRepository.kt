package br.thiago.githubapp.pullrequest_feature.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.thiago.githubapp.repositories_feature.domain.remote.PullRequestService

class PullRequestRepository(private val remoteService: PullRequestService) {

    fun getPullRequests(owner: String, repo: String) = Pager(config = PagingConfig(pageSize = 10)) {
        PullRequestPagingSource(
            remoteService,
            owner,
            repo
        )
    }.flow

}
