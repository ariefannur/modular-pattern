package com.github.ariefannur.modular.features.search.presentation

import com.github.ariefannur.modular.core.base.DataState
import com.github.ariefannur.modular.core.ui.BaseViewModel
import com.github.ariefannur.modular.features.search.domain.SearchUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUser: SearchUser
) : BaseViewModel() {

    private val _listUsers: MutableStateFlow<SearchState> = MutableStateFlow(SearchState.Start)
    val listUser: StateFlow<SearchState> = _listUsers

    fun doSearchUsers(name: String) {
        searchUser(name) {
            when(it) {
                is DataState.Loading -> _listUsers.value = SearchState.Loading
                is DataState.Success -> _listUsers.value = SearchState.Success(it.result)
                is DataState.Failure -> _listUsers.value = SearchState.Failed(it.message)
            }
        }
    }
}