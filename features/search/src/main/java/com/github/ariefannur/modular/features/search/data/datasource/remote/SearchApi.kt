package com.github.ariefannur.modular.features.search.data.datasource.remote

import com.github.ariefannur.modular.core.remote.response.ResponseUser
import com.github.ariefannur.modular.features.search.data.datasource.response.ResponseItemSearchUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SearchApi {
    @GET
    suspend fun getUser(@Url url: String) : Response<ResponseUser>

    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): Response<ResponseItemSearchUser>

}