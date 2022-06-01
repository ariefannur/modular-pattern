package com.github.ariefannur.modular.detail.presentation

import android.util.Log
import com.github.ariefannur.modular.core.base.DataState
import com.github.ariefannur.modular.core.ui.BaseViewModel
import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.GetDetailUser
import com.github.ariefannur.modular.detail.domain.GetUserRepo
import com.github.ariefannur.modular.detail.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val getUserDetail: GetDetailUser,
    private val getUserRepo: GetUserRepo
) : BaseViewModel() {


    private val _detailUser: MutableStateFlow<UserDetailState> = MutableStateFlow(UserDetailState.Loading)
    val detailUser: StateFlow<UserDetailState> = _detailUser


    private val _listRepo: MutableStateFlow<UserDetailRepoState> = MutableStateFlow(UserDetailRepoState.Loading)
    val listRepo: StateFlow<UserDetailRepoState> = _listRepo

    var avatar: String = ""

    fun getDetailUser(username: String) {
        getUserDetail(username) {
            when(it) {
                is DataState.Success -> {
                    avatar = it.result.avatar
                    _detailUser.value = UserDetailState.Success(it.result)
                }
                is DataState.Failure -> Log.d("AF", "ERROR :: ${it.message}")
            }
        }
        getUserRepo(username) {
            when(it) {
                is DataState.Success -> _listRepo.value = UserDetailRepoState.Success(it.result)
                is DataState.Failure -> Log.d("AF", "ERROR REPO ${it.message}")
            }
        }
    }
}