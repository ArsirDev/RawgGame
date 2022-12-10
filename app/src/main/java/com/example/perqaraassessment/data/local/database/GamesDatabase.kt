package com.example.perqaraassessment.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.perqaraassessment.data.local.dao.GameDao
import com.example.perqaraassessment.domain.model.Game

@Database(
    entities = [Game::class],
    version = GamesDatabase.dbVersion
)
abstract class GamesDatabase: RoomDatabase() {

    abstract val gameDao: GameDao

    companion object {
        @Volatile
        private var instance: GamesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { dbInstance ->
                this.instance = dbInstance
            }
        }

        private fun createDatabase(context: Context) = Room
            .databaseBuilder(context.applicationContext, GamesDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

        const val dbVersion = 1
        private const val dbName = "GamesDB"
    }
}