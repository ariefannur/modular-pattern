package com.github.ariefannur.modular.features.search.data.datasource.response

import com.github.ariefannur.modular.core.remote.response.ResponseUser
import com.github.ariefannur.modular.features.search.domain.User

fun ResponseUser.toUser(): User {
    return User(
        name = name.orEmpty(),
        username = login.orEmpty(),
        description = bio.orEmpty(),
        address = location.orEmpty(),
        avatar = avatar_url.orEmpty(),
        email = email.orEmpty()
    )
}
