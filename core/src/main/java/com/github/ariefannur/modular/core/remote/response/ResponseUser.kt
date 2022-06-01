package com.github.ariefannur.modular.core.remote.response

import com.squareup.moshi.Json

data class ResponseUser (
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "login") val login: String?,
    @field:Json(name = "avatar_url") val avatar_url: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "bio") val bio: String?,
    @field:Json(name = "followers") val followers: Int?,
    @field:Json(name = "following") val following: Int?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "location") val location: String?,
)