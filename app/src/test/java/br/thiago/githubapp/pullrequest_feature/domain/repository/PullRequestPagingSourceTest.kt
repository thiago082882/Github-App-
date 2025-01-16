package br.thiago.githubapp.pullrequest_feature.domain.repository

import androidx.paging.PagingSource
import br.thiago.githubapp.TestDispatcherRule
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequestsFactory
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.toModel
import br.thiago.githubapp.repositories_feature.domain.remote.PullRequestService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.mock.Calls

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PullRequestPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var service: PullRequestService

    private val pullRequestsPagingFactory =
        PullRequestsFactory.create(PullRequestsFactory.PullRequestsType.Default)

    private val pullRequestPagingSource by lazy {
        PullRequestPagingSource(service = service, owner = "owner", repo = "repo")
    }

    @Test
    fun `must return success load result when load is called`() = runBlocking {
        // Given
        whenever(service.getPullRequests(any(), any(), any())).thenReturn(
            Calls.response(
                pullRequestsPagingFactory
            )
        )

        // When
        val result = pullRequestPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val resultExpected = PagingSource.LoadResult.Page(
            data = pullRequestsPagingFactory.map { it.toModel() },
            prevKey = null,
            nextKey = if (pullRequestsPagingFactory.isEmpty()) null else 2
        )

        // Then
        assertThat(result).isEqualTo(resultExpected)
    }

    @Test
    fun `must return error load result when data source throws exception`() = runBlocking {
        // Given
        val exception = RuntimeException("Test exception")
        whenever(service.getPullRequests(any(), any(), any())).thenThrow(exception)

        // When
        val result = pullRequestPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // Then
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }
}
