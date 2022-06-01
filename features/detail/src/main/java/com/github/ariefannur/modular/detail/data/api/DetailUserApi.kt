package com.github.ariefannur.modular.detail.data.api

import com.github.ariefannur.modular.detail.data.api.response.ResponseRepository
import com.github.ariefannur.modular.detail.data.api.response.ResponseUserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailUserApi {

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Response<ResponseUserDetail>

    @GET("repos/{username}/repos")
    suspend fun getUserRepository(@Path("username") username: String): Response<List<ResponseRepository>>
}