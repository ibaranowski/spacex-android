package com.spacex.rockets.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = [
    Rocket::class,
    Launch::class
], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {


    companion object {
        const val DATABASE_NAME = "spacex-database"

        var database: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (database == null) {
                return create(context)!!
            }
            return database!!
        }

        fun create(context: Context): AppDatabase? {
            database = Room.databaseBuilder(context,
                    AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            return database
        }

        private fun doesDatabaseExists(context: Context): Boolean {
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            return dbFile.exists()
        }

        internal fun removeDataBase(context: Context) {
            context.deleteDatabase(AppDatabase.DATABASE_NAME)
            create(context)
        }
    }

    abstract val rocketDao: RocketDao
    abstract val launchesDao: LaunchesDao
}