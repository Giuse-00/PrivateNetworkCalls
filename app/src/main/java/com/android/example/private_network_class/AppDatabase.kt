package com.android.example.private_network_class

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.example.private_network_class.repository.dao.RepoDao
import com.android.example.private_network_class.repository.entity.RepoEntity

@Database(entities = [RepoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}