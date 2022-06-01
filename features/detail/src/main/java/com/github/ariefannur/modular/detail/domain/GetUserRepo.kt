package com.github.ariefannur.modular.detail.domain

import com.github.ariefannur.modular.core.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetUserRepo(
    private val repository: DetailUserRepository
    ): BaseUseCase<List<Repository>, String>() {

    override suspend fun run(params: String): Flow<List<Repository>> {
        return repository.getUserRepository(params)
    }
}