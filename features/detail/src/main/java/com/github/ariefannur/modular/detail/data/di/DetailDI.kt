package com.github.ariefannur.modular.detail.data.di

import com.github.ariefannur.modular.core.database.Database
import com.github.ariefannur.modular.core.infra.Service
import com.github.ariefannur.modular.detail.data.api.DetailUserApi
import com.github.ariefannur.modular.detail.data.datasource.LocalDetailUserImpl
import com.github.ariefannur.modular.detail.data.datasource.RemoteDetailUserImpl
import com.github.ariefannur.modular.detail.data.repository.DetailUserRepositoryImpl
import com.github.ariefannur.modular.detail.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDI {

    @Provides
    @Singleton
    fun provideService(): DetailUserApi = Service().build().create(DetailUserApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        api: DetailUserApi
    ): RemoteDetailUser = RemoteDetailUserImpl(api)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: Database
    ): LocalDetailUser = LocalDetailUserImpl(database.userDetailDao(), database.userRepoDao())

    @Provides
    @Singleton
    fun provideDetailRepository(
        local: LocalDetailUser,
        remote: RemoteDetailUser
    ): DetailUserRepository = DetailUserRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideGetDetailUser(repo: DetailUserRepository): GetDetailUser = GetDetailUser(repo)

    @Provides
    @Singleton
    fun provideGetRepoUser(repo: DetailUserRepository): GetUserRepo = GetUserRepo(repo)


}