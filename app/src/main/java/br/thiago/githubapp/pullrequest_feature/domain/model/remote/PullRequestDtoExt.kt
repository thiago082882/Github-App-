package br.thiago.githubapp.pullrequest_feature.domain.model.remote

import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import br.thiago.githubapp.pullrequest_feature.domain.model.User


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