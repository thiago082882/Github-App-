package br.thiago.githubapp.repositories_feature.domain.presentation.screens.home

import androidx.paging.PagingData
import br.thiago.githubapp.TestDispatcherRule
import br.thiago.githubapp.repositories_feature.domain.model.RepositoriesFactory
import br.thiago.githubapp.repositories_feature.domain.model.remote.toModel
import br.thiago.githubapp.repositories_feature.domain.usecases.GetRepositoryUseCase
import br.thiago.githubapp.repositories_feature.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RepositoriesViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getRepositoryUseCase: GetRepositoryUseCase

    private val viewModel by lazy {
        HomeViewModel(getRepositoryUseCase = getRepositoryUseCase)
    }

    private val fakePagingDataRepositories = PagingData.from(
        RepositoriesFactory.create(RepositoriesFactory.RepositoriesType.Default).items.map { it.toModel() }
    )

    @Test
    fun `must validate paging data object values when calling paging data from github repositories`() = runBlockingTest {
        // Given
        whenever(getRepositoryUseCase.invoke()).thenReturn(
            flowOf(fakePagingDataRepositories)
        )

        // When
        val result = viewModel.repository.first()

        // Then
        assertThat(result).isNotNull()
    }

    @Test
    fun `must throw an exception when the calling to the use case returns an exception`() = runBlockingTest {
        // Given
        whenever(getRepositoryUseCase.invoke()).thenThrow(RuntimeException())

        // When
        val result = viewModel.repository

        // Then
        assertThat(result).isNotSameInstanceAs(fakePagingDataRepositories)
    }
}
