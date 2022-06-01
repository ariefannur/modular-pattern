package com.github.ariefannur.modular.detail.data.datasource

import android.util.Log
import com.github.ariefannur.modular.detail.data.api.DetailUserApi
import com.github.ariefannur.modular.detail.data.api.response.toUserDetail
import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.RemoteDetailUser
import com.github.ariefannur.modular.detail.domain.Repository

class RemoteDetailUserImpl(private val service: DetailUserApi): RemoteDetailUser {
    override suspend fun requestDetailUser(username: String): DetailUser {
        val remote = service.getDetailUser(username)
        if (remote.isSuccessful) {
            return remote.body()?.toUserDetail() ?: throw Exception("Empty data")
        } else {
            Log.d("AF", "error get detail ${remote.errorBody()}")
            throw Exception(remote.message())
        }
    }

    override suspend fun requestRepository(username: String): List<Repository> {
        val remote = service.getUserRepository(username)
        if (remote.isSuccessful) {
            return remote.body()?.map { it.toRepository() } ?: throw Exception("Empty data")
        } else {
            throw Exception(remote.message())
        }
    }
}