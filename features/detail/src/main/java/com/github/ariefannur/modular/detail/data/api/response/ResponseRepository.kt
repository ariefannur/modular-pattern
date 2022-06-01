package com.github.ariefannur.modular.detail.data.api.response

import com.github.ariefannur.modular.detail.domain.Repository
import com.squareup.moshi.Json

data class ResponseRepository (
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "stargazers_count") val star: Int?,
    @field:Json(name = "updated_at") val updated_at: String?,
    ) {
    fun toRepository(): Repository {
        return Repository(id?: 0, name.orEmpty(), description.orEmpty(), star ?: 0, updated_at.orEmpty())
    }
}