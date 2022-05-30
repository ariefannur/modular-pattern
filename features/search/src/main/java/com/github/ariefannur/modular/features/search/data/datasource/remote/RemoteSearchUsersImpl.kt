package com.github.ariefannur.modular.features.search.data.datasource.remote

import com.github.ariefannur.modular.features.search.domain.RemoteSearchUsers
import com.github.ariefannur.modular.features.search.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSearchUsersImpl(private val service: SearchApi): RemoteSearchUsers {
    override suspend fun requestSearchUsers(query: String): List<User> {
        val response = service.searchUser(query)
        if (response.isSuccessful) {
            return response.body()?.let {
                 if( it.items.isNotEmpty()) {
                     return it.items.map { set ->
                        withContext(Dispatchers.IO) {
                            set.url?.let { url ->
                                service.getUser(url)
                            }
                        }
                    }.map { response ->
                        response?.body()!!.toUser()
                    }
                } else {
                     listOf()
                }
            } ?: listOf()
        } else {
            throw Exception(response.message())
        }
    }
}