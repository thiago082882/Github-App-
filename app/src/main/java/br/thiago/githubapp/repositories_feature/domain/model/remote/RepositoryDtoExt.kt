package br.thiago.githubapp.repositories_feature.domain.model.remote

import br.thiago.githubapp.repositories_feature.domain.model.Owner
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import br.thiago.githubapp.repositories_feature.domain.model.Repository
import br.thiago.githubapp.pullrequest_feature.domain.model.User
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.PullRequestDto
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.UserDto


fun RepositoryDto.toModel(): Repository =
    Repository(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.toModel(),
        stargazers_count = this.stargazersCount,
        forks_count = this.forksCount
    )

fun OwnerDto.toModel(): Owner =
    Owner(
        login = this.login,
        avatar_url = this.avatarUrl
    )

fun PullRequestDto.toModel(): PullRequest = PullRequest(
    id = this.id,
    title = this.title,
    user = this.user.toModel(),
    createdAt = this.createdAt,
    body = this.body
)

fun UserDto.toModel(): User =
    User(
        login = this.login,
        avatarUrl = this.avatarUrl
    )