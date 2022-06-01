package com.github.ariefannur.modular.detail.domain

import com.github.ariefannur.modular.core.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetDetailUser(
    private val repository: DetailUserRepository
): BaseUseCase<DetailUser, String>() {
    override suspend fun run(params: String): Flow<DetailUser> {
        return repository.getDetailUser(params)
    }
}