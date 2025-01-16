package br.thiago.githubapp.pullrequest_feature.domain.model

import br.thiago.githubapp.pullrequest_feature.domain.model.remote.PullRequestDto
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.UserDto

class PullRequestsFactory {

    companion object {
        fun create(pullRequestsType: PullRequestsType): List<PullRequestDto> =
            when (pullRequestsType) {
                is PullRequestsType.Default -> {
                    listOf(
                        PullRequestDto(
                            id = 1L,
                            title = "fakeTitle",
                            user = UserDto(login = "fakeLogin", avatarUrl = "fakeUrl"),
                            createdAt = "2023-01-01T00:00:00Z",
                            body = "fakeBody"
                        ),
                        PullRequestDto(
                            id = 2L,
                            title = "fakeTitle2",
                            user = UserDto(login = "fakeLogin2", avatarUrl = "fakeUrl2"),
                            createdAt = "2023-01-02T00:00:00Z",
                            body = "fakeBody2"
                        ),
                        PullRequestDto(
                            id = 3L,
                            title = "fakeTitle3",
                            user = UserDto(login = "fakeLogin3", avatarUrl = "fakeUrl3"),
                            createdAt = "2023-01-03T00:00:00Z",
                            body = "fakeBody3"
                        )
                    )
                }

                is PullRequestsType.EmptyListPullRequests -> listOf()
            }
    }

    sealed class PullRequestsType {
        data object EmptyListPullRequests : PullRequestsType()
        data object Default : PullRequestsType()
    }
}
