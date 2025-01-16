package br.thiago.githubapp.repositories_feature.domain.model

data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val owner: Owner,
    val stargazers_count: Int,
    val forks_count: Int
)




