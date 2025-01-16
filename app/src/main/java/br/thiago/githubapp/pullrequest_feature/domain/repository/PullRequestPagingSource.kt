package br.thiago.githubapp.pullrequest_feature.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.toModel
import br.thiago.githubapp.repositories_feature.domain.remote.PullRequestService
import retrofit2.awaitResponse

class PullRequestPagingSource(
    private val service: PullRequestService,
    private val owner: String,
    private val repo: String
) : PagingSource<Int, PullRequest>() {

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        val page = params.key ?: 1

        return try {
            val response = service.getPullRequests(owner, repo, page).awaitResponse()
            val pullRequests = if (response.isSuccessful) {
                response.body()?.map { it.toModel() } ?: emptyList()
            } else {
                emptyList()
            }

            LoadResult.Page(
                data = pullRequests,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (pullRequests.isEmpty()) null else page + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}
