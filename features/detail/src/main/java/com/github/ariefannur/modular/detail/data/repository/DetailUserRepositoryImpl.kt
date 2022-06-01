package com.github.ariefannur.modular.detail.data.repository

import com.github.ariefannur.modular.detail.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailUserRepositoryImpl(
    private val remote: RemoteDetailUser,
    private val local: LocalDetailUser
): DetailUserRepository {
    override suspend fun getDetailUser(username: String): Flow<DetailUser> = flow {
        with(local.getDetailUser(username)) {
            if (this.name != "") emit(this)
        }
        with(remote.requestDetailUser(username)) {
            if (this.name != ""){
                emit(this)
                local.cacheDetailUser(this)
            }
        }
    }

    override suspend fun getUserRepository(username: String): Flow<List<Repository>>  = flow {
        with(local.getUserRepository(username)) {
            if (this.isNotEmpty()) emit(this)
        }
        with(remote.requestRepository(username)) {
            if (this.isNotEmpty()){
                emit(this)
                local.cacheUserRepository(username,this)
            }
        }
    }
}