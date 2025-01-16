package br.thiago.githubapp.repositories_feature.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.thiago.githubapp.repositories_feature.domain.model.Repository
import br.thiago.githubapp.repositories_feature.domain.usecases.GetRepositoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeViewModel(private val getRepositoryUseCase: GetRepositoryUseCase) : ViewModel() {

    private val _repository = MutableStateFlow<PagingData<Repository>>(PagingData.empty())


    val repository: StateFlow<PagingData<Repository>> get() = _repository.asStateFlow()

    init {
        viewModelScope.launch {
            getRepositoryUseCase()
                .cachedIn(viewModelScope)
                .collectLatest(_repository::emit)
        }
    }
}
