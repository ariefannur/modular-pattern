package com.github.ariefannur.modular.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "db-app").build()
    }
}