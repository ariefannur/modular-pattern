package com.github.ariefannur.modular.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryEntity (
    @PrimaryKey
    val id: Int,
    val username: String,
    val name: String,
    val description: String,
    val star: Int,
    val updateAt: String
    )