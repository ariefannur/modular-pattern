package com.github.ariefannur.modular.detail.data.api.response

import com.github.ariefannur.modular.detail.domain.DetailUser
import com.squareup.moshi.Json

data class ResponseUserDetail (
    @field:Json(name = "username") val username: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "avatar_url") val avatar: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "address") val address: String?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "following") val following: Int?,
    @field:Json(name = "follower") val follower: Int?,
    ) {
    fun toUserDetail(): DetailUser {
        return DetailUser(
            username.orEmpty(),
            name.orEmpty(),
            avatar.orEmpty(),
            description.orEmpty(),
            address.orEmpty(),
            email.orEmpty(),
            following ?: 0,
            follower ?: 0

        )
    }
}