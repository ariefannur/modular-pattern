package com.github.ariefannur.modular.detail.data.datasource

import com.github.ariefannur.modular.core.database.UserDetailDao
import com.github.ariefannur.modular.core.database.UserRepoDao
import com.github.ariefannur.modular.detail.data.api.response.toRepository
import com.github.ariefannur.modular.detail.data.api.response.toUserDetail
import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.LocalDetailUser
import com.github.ariefannur.modular.detail.domain.Repository

class LocalDetailUserImpl(
    private val userDetailDao: UserDetailDao,
    private val userRepoDao: UserRepoDao
    ): LocalDetailUser {
    override suspend fun getDetailUser(username: String): DetailUser? {
        return with(userDetailDao.getUserDetail(username)) {
            if (this.isNullOrEmpty()) null else this[0].toUserDetail()
        }
    }

    override suspend fun cacheDetailUser(detail: DetailUser) {
        userDetailDao.saveUserDetail(detail.toUserDetailEntity())
    }

    override suspend fun getUserRepository(username: String): List<Repository> {
        return userRepoDao.getUserRepository(username).map { it.toRepository() }
    }

    override suspend fun cacheUserRepository(username: String, data: List<Repository>) {
        userRepoDao.saveUserRepository(data.map { it.toRepositoryEntity(username) })
    }
}