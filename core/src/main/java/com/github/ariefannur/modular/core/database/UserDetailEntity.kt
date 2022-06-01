package com.github.ariefannur.modular.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetailEntity (
    @PrimaryKey
    val username: String,
    val name: String,
    val avatar: String,
    val description: String,
    val address: String,
    val email: String,
    val following: Int,
    val follower: Int
)
