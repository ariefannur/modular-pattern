package com.github.ariefannur.modular.detail.domain

interface RemoteDetailUser {
    suspend fun requestDetailUser(username: String): DetailUser
    suspend fun requestRepository(username: String): List<Repository>
}