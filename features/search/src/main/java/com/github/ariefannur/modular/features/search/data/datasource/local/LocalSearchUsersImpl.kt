package com.github.ariefannur.modular.features.search.data.datasource.local

import com.github.ariefannur.modular.core.database.UserDao
import com.github.ariefannur.modular.features.search.domain.LocalSearchUsers
import com.github.ariefannur.modular.features.search.domain.User

class LocalSearchUsersImpl(private val userDao: UserDao):  LocalSearchUsers {

    override suspend fun getCacheUsers(query: String): List<User> {
        return userDao.searchUser(query).map { it.toUser() }
    }

    override suspend fun saveCacheUsers(users: List<User>) {
        userDao.saveUser(users.map {it.toUserEntity()})
    }
}