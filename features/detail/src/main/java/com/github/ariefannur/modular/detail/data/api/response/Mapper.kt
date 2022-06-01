package com.github.ariefannur.modular.detail.data.api.response

import com.github.ariefannur.modular.core.database.RepositoryEntity
import com.github.ariefannur.modular.core.database.UserDetailEntity
import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.Repository

fun UserDetailEntity.toUserDetail(): DetailUser {
    return DetailUser(
        name,
        username,
        avatar,
        description,
        address,
        email,
        following,
        follower
    )
}

fun RepositoryEntity.toRepository(): Repository {
    return Repository(
        id,
        name,
        description,
        star,
        updateAt
    )
}