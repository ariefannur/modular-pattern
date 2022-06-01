package com.github.ariefannur.modular.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, UserDetailEntity::class, RepositoryEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailDao(): UserDetailDao
    abstract fun userRepoDao(): UserRepoDao
}