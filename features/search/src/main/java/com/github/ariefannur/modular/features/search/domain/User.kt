package com.github.ariefannur.modular.features.search.domain

import com.github.ariefannur.modular.features.search.data.datasource.local.UserEntity

data class User (
        val name: String,
        val username: String,
        val avatar: String,
        val description: String,
        val address: String,
        val email: String ) {

        fun toUserEntity(): UserEntity {
                return UserEntity(name, username, avatar, description, address, email)
        }
}