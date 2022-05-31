package com.github.ariefannur.modular.features.search.presentation

import com.github.ariefannur.modular.features.search.domain.User

sealed class SearchState {
    object Loading: SearchState()
    data class Success(val list: List<User>): SearchState()
    data class Failed(val message: String): SearchState()
    object Start: SearchState()
}