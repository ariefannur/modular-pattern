package com.github.ariefannur.modular.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserRepoDao {

    @Query("SELECT * FROM RepositoryEntity WHERE username = :username")
    fun getUserRepository(username: String) : List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserRepository(data: List<RepositoryEntity>)
}