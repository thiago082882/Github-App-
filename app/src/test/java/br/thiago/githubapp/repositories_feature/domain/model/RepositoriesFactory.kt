package br.thiago.githubapp.repositories_feature.domain.model

import br.thiago.githubapp.repositories_feature.domain.model.remote.OwnerDto
import br.thiago.githubapp.repositories_feature.domain.model.remote.RepositoryDto
import br.thiago.githubapp.repositories_feature.domain.model.remote.RepositoryResponseDto

class RepositoriesFactory {

    companion object {
        fun create(repositoryType: RepositoriesType) = when (repositoryType) {
            is RepositoriesType.Default -> {
                RepositoryResponseDto(
                    totalCount = 200,
                    incompleteResults = false,
                    items = listOf(
                        RepositoryDto(
                            id = 1L,
                            name = "fake name",
                            fullName = "fake full name",
                            description = "fake description",
                            owner = OwnerDto(
                                login = "fake login",
                                avatarUrl = "fake url"
                            ),
                            stargazersCount = 10,
                            forksCount = 10
                        ),
                        RepositoryDto(
                            id = 2L,
                            name = "fake name 2",
                            fullName = "fake full name 2",
                            description = "fake description 2",
                            owner = OwnerDto(
                                login = "fake login 2",
                                avatarUrl = "fake url 2"
                            ),
                            stargazersCount = 20,
                            forksCount = 20
                        ),
                        RepositoryDto(
                            id = 3L,
                            name = "fake name 3",
                            fullName = "fake full name 3",
                            description = "fake description 3",
                            owner = OwnerDto(
                                login = "fake login 3",
                                avatarUrl = "fake url 3"
                            ),
                            stargazersCount = 30,
                            forksCount = 30
                        )
                    )
                )
            }

            is RepositoriesType.EmptyListRepositories -> RepositoryResponseDto(
                totalCount = 0,
                incompleteResults = false,
                items = emptyList()
            )
        }
    }

    sealed class RepositoriesType {
        data object EmptyListRepositories : RepositoriesType()
        data object Default : RepositoriesType()
    }
}
