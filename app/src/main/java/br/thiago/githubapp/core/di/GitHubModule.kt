package br.thiago.githubapp.core.di

import br.thiago.githubapp.pullrequest_feature.domain.repository.PullRequestRepository
import br.thiago.githubapp.pullrequest_feature.domain.usecases.GetPullRequestUseCase
import br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request.PullRequestViewModel
import br.thiago.githubapp.repositories_feature.domain.repository.GithubRepository
import br.thiago.githubapp.repositories_feature.domain.usecases.GetRepositoryUseCase
import br.thiago.githubapp.repositories_feature.presentation.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels =
    module {
        viewModel { HomeViewModel(getRepositoryUseCase = get<GetRepositoryUseCase>()) }
        viewModel { PullRequestViewModel(getPullRequestsUseCase = get<GetPullRequestUseCase>()) }

    }

val useCases =
    module {
        factory { GetRepositoryUseCase(repository = get<GithubRepository>()) }
        factory { GetPullRequestUseCase(repository = get<PullRequestRepository>()) }


    }
