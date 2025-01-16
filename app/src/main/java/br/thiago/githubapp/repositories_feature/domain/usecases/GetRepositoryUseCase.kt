package br.thiago.githubapp.repositories_feature.domain.usecases

import androidx.paging.PagingData
import br.thiago.githubapp.repositories_feature.domain.model.Repository
import br.thiago.githubapp.repositories_feature.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetRepositoryUseCase(private val repository: GithubRepository) {
    operator fun invoke(): Flow<PagingData<Repository>> = repository.getRepositories()
}