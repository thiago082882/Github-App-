package br.thiago.githubapp.repositories_feature.domain.remote

import br.thiago.githubapp.core.utils.Constants.PULL_REQUEST_ENDPOINT
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.PullRequestDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullRequestService {

    @GET(PULL_REQUEST_ENDPOINT)
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int
    ): Call<List<PullRequestDto>>
}

