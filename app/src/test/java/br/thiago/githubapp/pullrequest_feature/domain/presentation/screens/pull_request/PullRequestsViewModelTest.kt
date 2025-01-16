package br.thiago.githubapp.pullrequest_feature.domain.presentation.screens.pull_request

import androidx.paging.PagingData
import br.thiago.githubapp.TestDispatcherRule
import br.thiago.githubapp.core.utils.UiState
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequestsFactory
import br.thiago.githubapp.pullrequest_feature.domain.model.remote.toModel
import br.thiago.githubapp.pullrequest_feature.domain.usecases.GetPullRequestUseCase
import br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request.PullRequestViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner.Silent::class)
class PullRequestsViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getPullRequestsUseCase: GetPullRequestUseCase

    private val viewModel by lazy {
        PullRequestViewModel(getPullRequestsUseCase = getPullRequestsUseCase)
    }

    private val fakePullRequests =
        PullRequestsFactory.create(PullRequestsFactory.PullRequestsType.Default).map { it.toModel() }

    @Test
    fun `must validate paging data object values when calling paging data from pull requests`() = runTest {
        // Given
        val pagingData = PagingData.from(fakePullRequests)
        whenever(getPullRequestsUseCase.invoke(any(), any())).thenReturn(
            flowOf(pagingData)
        )

        // When
        viewModel.fetchPullRequests("owner", "repo")
        val result = viewModel.uiState.first()

        // Then
        assertThat(result).isInstanceOf(UiState.Result::class.java)
        assertThat((result as UiState.Result).item).isEqualTo(pagingData)
        verify(getPullRequestsUseCase).invoke(any(), any())
    }

    @Test
    fun `must handle exception when the calling to the use case returns an exception`() = runTest {
        // Given
        val exception = RuntimeException("Test exception")
        whenever(getPullRequestsUseCase.invoke(any(), any())).thenThrow(exception)

        // When
        viewModel.fetchPullRequests("owner", "repo")
        val result = viewModel.uiState.first()

        // Then
        assertThat(result).isInstanceOf(UiState.Error::class.java)
        verify(getPullRequestsUseCase).invoke(any(), any())
    }
}
