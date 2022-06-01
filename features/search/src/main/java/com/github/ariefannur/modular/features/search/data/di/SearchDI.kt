package com.github.ariefannur.modular.features.search.data.di

import com.github.ariefannur.modular.core.infra.Service
import com.github.ariefannur.modular.core.database.Database
import com.github.ariefannur.modular.features.search.data.datasource.local.LocalSearchUsersImpl
import com.github.ariefannur.modular.features.search.data.datasource.remote.RemoteSearchUsersImpl
import com.github.ariefannur.modular.features.search.data.datasource.remote.SearchApi
import com.github.ariefannur.modular.features.search.data.repository.SearchRepositoryImpl
import com.github.ariefannur.modular.features.search.domain.LocalSearchUsers
import com.github.ariefannur.modular.features.search.domain.RemoteSearchUsers
import com.github.ariefannur.modular.features.search.domain.SearchRepository
import com.github.ariefannur.modular.features.search.domain.SearchUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDI {

    @Provides
    @Singleton
    fun provideService(): SearchApi = Service().build().create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        api: SearchApi
    ): RemoteSearchUsers = RemoteSearchUsersImpl(api)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: Database
    ): LocalSearchUsers = LocalSearchUsersImpl(database.userDao())

    @Provides
    @Singleton
    fun provideSearchRepository(
        local: LocalSearchUsers,
        remote: RemoteSearchUsers
    ): SearchRepository = SearchRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideSearchUser(repo: SearchRepository): SearchUser = SearchUser(repo)


}