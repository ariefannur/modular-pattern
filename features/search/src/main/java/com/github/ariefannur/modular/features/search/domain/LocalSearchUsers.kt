package com.github.ariefannur.modular.features.search.domain

interface LocalSearchUsers {
    suspend fun getCacheUsers(query: String): List<User>
    suspend fun saveCacheUsers(users: List<User>)
}