package com.github.ariefannur.modular.features.search.domain

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchUsers(queryName: String): Flow<List<User>>
}