package com.github.ariefannur.modular.features.search.data.datasource.local

import com.github.ariefannur.modular.core.database.UserEntity
import com.github.ariefannur.modular.features.search.domain.User

fun UserEntity.toUser(): User {
    return User(
        name, username, avatar, description, address, email
    )
}