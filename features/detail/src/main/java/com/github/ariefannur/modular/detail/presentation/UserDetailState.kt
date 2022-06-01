package com.github.ariefannur.modular.detail.presentation

import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.Repository

sealed class UserDetailState {
    object Loading : UserDetailState()
    data class Success(val detail: DetailUser) : UserDetailState()
}


sealed class UserDetailRepoState {
    object Loading : UserDetailRepoState()
    data class Success(val data: List<Repository>) : UserDetailRepoState()
}
