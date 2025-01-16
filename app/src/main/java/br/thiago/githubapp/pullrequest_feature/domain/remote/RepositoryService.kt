package br.thiago.githubapp.pullrequest_feature.domain.remote

import br.thiago.githubapp.core.utils.Constants.REPOSITORY_ENDPOINT
import br.thiago.githubapp.repositories_feature.domain.model.remote.RepositoryResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET(REPOSITORY_ENDPOINT)
    fun getRepositories(
        @Query("page") page: Int
    ): Call<RepositoryResponseDto>

}

