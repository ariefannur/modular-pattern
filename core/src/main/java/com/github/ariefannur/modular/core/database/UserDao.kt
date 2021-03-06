package com.github.ariefannur.modular.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE username = :username")
    fun getDetailUser(username: String): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE username LIKE '%' || :name || '%'")
    fun searchUser(name: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: List<UserEntity>)
}