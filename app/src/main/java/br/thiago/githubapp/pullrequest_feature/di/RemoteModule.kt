package br.thiago.githubapp.pullrequest_feature.di

import br.thiago.githubapp.core.utils.Constants.BASE_URL
import br.thiago.githubapp.core.utils.Constants.TIMEOUT_SECONDS
import br.thiago.githubapp.pullrequest_feature.domain.repository.PullRequestRepository
import br.thiago.githubapp.repositories_feature.domain.remote.PullRequestService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModulePullRequest
    get() = module {
        single { PullRequestRepository(pullRequestRepository) }
    }

private val httpClient
    get() = OkHttpClient.Builder().readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).build()

private val retrofit
    get() = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(httpClient)
        .baseUrl(BASE_URL).build()

private val pullRequestRepository get() = retrofit.create(PullRequestService::class.java)
