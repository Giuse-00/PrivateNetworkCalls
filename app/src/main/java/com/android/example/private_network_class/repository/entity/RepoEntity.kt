package com.android.example.private_network_class.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class RepoEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "pokemon_name") val name: String)