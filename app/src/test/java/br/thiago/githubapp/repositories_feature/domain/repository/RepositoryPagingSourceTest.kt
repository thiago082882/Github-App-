package br.thiago.githubapp.repositories_feature.domain.repository

import androidx.paging.PagingSource
import br.thiago.githubapp.TestDispatcherRule
import br.thiago.githubapp.pullrequest_feature.domain.remote.RepositoryService
import br.thiago.githubapp.repositories_feature.domain.model.RepositoriesFactory
import br.thiago.githubapp.repositories_feature.domain.model.remote.toModel
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
class RepositoryPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var service: RepositoryService

    private val repositoriesPagingFactory =
        RepositoriesFactory.create(RepositoriesFactory.RepositoriesType.Default)

    private val repositoriesPagingSource by lazy {
        RepositoryPagingSource(service = service)
    }

    @Test
    fun `must return success load result when load is called`() = runBlocking {
        // Given
        whenever(service.getRepositories(any())).thenReturn(Calls.response(repositoriesPagingFactory))

        // When
        val result = repositoriesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val resultExpected = PagingSource.LoadResult.Page(
            data = repositoriesPagingFactory.items.map { it.toModel() },
            prevKey = null,
            nextKey = if (repositoriesPagingFactory.items.isEmpty()) null else 2
        )

        // Then
        assertThat(result).isEqualTo(resultExpected)
    }

    @Test
    fun `must return error load result when data source throws exception`() = runBlocking {
        // Given
        val exception = RuntimeException("Test exception")
        whenever(service.getRepositories(any())).thenThrow(exception)

        // When
        val result = repositoriesPagingSource.load(
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
