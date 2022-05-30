package com.github.ariefannur.modular.features.search.data.repository

import android.util.Log
import com.github.ariefannur.modular.features.search.domain.LocalSearchUsers
import com.github.ariefannur.modular.features.search.domain.RemoteSearchUsers
import com.github.ariefannur.modular.features.search.domain.SearchRepository
import com.github.ariefannur.modular.features.search.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val remote: RemoteSearchUsers,
    private val local: LocalSearchUsers): SearchRepository {
    override suspend fun searchUsers(queryName: String): Flow<List<User>> = flow {
        with(local.getCacheUsers(queryName)) {
            Log.d("AF", "LOCAL $this")
            if (this.isNotEmpty()) {
                emit(this)
            } else {
                with(remote.requestSearchUsers(queryName)) {
                    if (this.isNotEmpty()) {
                        emit(this)
                        local.saveCacheUsers(this)
                    }
                }
            }
        }


    }
}