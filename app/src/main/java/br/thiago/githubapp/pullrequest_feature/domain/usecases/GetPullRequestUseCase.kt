package br.thiago.githubapp.pullrequest_feature.domain.usecases

import androidx.paging.PagingData
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import br.thiago.githubapp.pullrequest_feature.domain.repository.PullRequestRepository
import kotlinx.coroutines.flow.Flow

class GetPullRequestUseCase(private val repository: PullRequestRepository) {
    operator fun invoke(owner: String, repo: String): Flow<PagingData<PullRequest>> =
        repository.getPullRequests(owner, repo)
}