package com.github.ariefannur.modular.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDetailDao {

   @Query("SELECT * FROM UserDetailEntity WHERE username = :username")
    fun getUserDetail(username: String) : UserDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserDetail(userDetailEntity: UserDetailEntity)
}