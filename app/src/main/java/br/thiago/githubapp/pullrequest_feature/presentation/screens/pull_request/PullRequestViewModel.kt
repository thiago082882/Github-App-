package br.thiago.githubapp.pullrequest_feature.presentation.screens.pull_request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.thiago.githubapp.core.utils.UiState
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import br.thiago.githubapp.pullrequest_feature.domain.usecases.GetPullRequestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PullRequestViewModel(private val getPullRequestsUseCase: GetPullRequestUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<PagingData<PullRequest>>>(UiState.Loading)
    val uiState: StateFlow<UiState<PagingData<PullRequest>>> get() = _uiState.asStateFlow()


    fun fetchPullRequests(owner: String, repo: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                getPullRequestsUseCase(owner, repo).cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _uiState.value = UiState.Result(pagingData)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error
            }
        }
    }
}
