package com.github.ariefannur.modular.detail.domain

import com.github.ariefannur.modular.core.database.RepositoryEntity

data class Repository (
    val id: Int,
    val name: String,
    val description: String,
    val star: Int,
    val updateAt: String
) {
    fun toRepositoryEntity(username: String): RepositoryEntity {
        return RepositoryEntity(id, username, name, description, star, updateAt)
    }
}