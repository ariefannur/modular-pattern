package com.github.ariefannur.modular.detail.domain

import com.github.ariefannur.modular.core.database.UserDetailEntity

data class DetailUser (
    val name: String,
    val username: String,
    val avatar: String,
    val description: String,
    val address: String,
    val email: String,
    val following: Int,
    val follower: Int
) {

    fun toUserDetailEntity(): UserDetailEntity {
        return UserDetailEntity(
            name = name, username = username,
            avatar = avatar, address =  address,
            email = email, follower = follower,
            following = following, description = description)
    }
}