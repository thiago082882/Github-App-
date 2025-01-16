package br.thiago.githubapp.repositories_feature.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.thiago.githubapp.pullrequest_feature.domain.remote.RepositoryService
import br.thiago.githubapp.repositories_feature.domain.model.Repository
import br.thiago.githubapp.repositories_feature.domain.model.remote.toModel
import retrofit2.awaitResponse

class RepositoryPagingSource(
    private val service: RepositoryService
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val page = params.key ?: 1

        return try {
            val response = service.getRepositories(page).awaitResponse()
            val repositories = if (response.isSuccessful) {
                response.body()?.items?.map { it.toModel() } ?: emptyList()
            } else {
                emptyList()
            }

            LoadResult.Page(
                data = repositories,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repositories.isEmpty()) null else page + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}
