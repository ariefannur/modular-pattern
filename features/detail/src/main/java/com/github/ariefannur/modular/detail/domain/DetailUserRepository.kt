package com.github.ariefannur.modular.detail.domain

import kotlinx.coroutines.flow.Flow

interface DetailUserRepository {
    suspend fun getDetailUser(username: String): Flow<DetailUser>
    suspend fun getUserRepository(username: String): Flow<List<Repository>>
}