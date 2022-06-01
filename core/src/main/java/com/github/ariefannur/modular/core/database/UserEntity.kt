package com.github.ariefannur.modular.features.search.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ariefannur.modular.features.search.domain.User

@Entity
data class UserEntity (
    val name: String,
    @PrimaryKey
    val username: String,
    val avatar: String,
    val description: String,
    val address: String,
    val email: String
) {
    fun toUser(): User {
        return User(
            name, username, avatar, description, address, email
        )
    }
}