package com.github.ariefannur.modular.detail.domain

interface LocalDetailUser {
    suspend fun getDetailUser(username: String): DetailUser
    suspend fun cacheDetailUser(detail: DetailUser)
    suspend fun getUserRepository(username: String): List<Repository>
    suspend fun cacheUserRepository(username: String, data: List<Repository>)
}