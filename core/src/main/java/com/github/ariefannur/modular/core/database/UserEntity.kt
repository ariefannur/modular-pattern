package com.github.ariefannur.modular.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    val name: String,
    @PrimaryKey
    val username: String,
    val avatar: String,
    val description: String,
    val address: String,
    val email: String
)