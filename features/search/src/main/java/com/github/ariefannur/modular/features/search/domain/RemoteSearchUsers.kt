package com.github.ariefannur.modular.features.search.domain

interface RemoteSearchUsers {
    suspend fun requestSearchUsers(query: String): List<User>
}