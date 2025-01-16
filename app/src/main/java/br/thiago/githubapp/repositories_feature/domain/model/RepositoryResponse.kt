package br.thiago.githubapp.repositories_feature.domain.model

data class RepositoryResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repository>
)
