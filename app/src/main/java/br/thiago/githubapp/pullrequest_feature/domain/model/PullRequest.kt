package br.thiago.githubapp.pullrequest_feature.domain.model

data class PullRequest(
    val id: Long,
    val title: String,
    val user: User,
    val createdAt: String,
    val body: String
)

data class User(
    val login: String,
    val avatarUrl: String
)
