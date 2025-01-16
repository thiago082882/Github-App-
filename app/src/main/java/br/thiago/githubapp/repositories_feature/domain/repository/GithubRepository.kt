package br.thiago.githubapp.repositories_feature.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.thiago.githubapp.pullrequest_feature.domain.remote.RepositoryService

class GithubRepository(
    private val remoteService: RepositoryService,
) {

    fun getRepositories() =
        Pager(config = PagingConfig(pageSize = 50)) {
            RepositoryPagingSource(remoteService)
        }.flow


}
