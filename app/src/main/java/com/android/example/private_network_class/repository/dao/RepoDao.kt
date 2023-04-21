package com.android.example.private_network_class.repository.dao

import androidx.room.*
import com.android.example.private_network_class.repository.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Query("SELECT * FROM pokemon_name")
    fun getAll(): List<RepoEntity>

    @Insert
    suspend fun insert(name: RepoEntity)

    @Delete
    suspend fun delete(name: RepoEntity)

    @Update
    suspend fun update(name: RepoEntity)
}