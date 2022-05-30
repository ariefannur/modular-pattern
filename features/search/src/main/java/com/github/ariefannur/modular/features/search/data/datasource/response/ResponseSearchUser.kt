package com.github.ariefannur.modular.features.search.data.datasource.response

import com.squareup.moshi.Json

data class ResponseSearchUser (
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "login") val login: String?,
    @field:Json(name = "url") val url: String?
)

data class ResponseItemSearchUser(
    @field:Json(name = "items") val items: List<ResponseSearchUser>
)