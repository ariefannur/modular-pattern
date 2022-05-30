package com.github.ariefannur.modular.features.search.domain

import com.github.ariefannur.modular.core.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class SearchUser(private val repository: SearchRepository): BaseUseCase<List<User>, String>() {
    override suspend fun run(params: String): Flow<List<User>> {
        return repository.searchUsers(params)
    }
}