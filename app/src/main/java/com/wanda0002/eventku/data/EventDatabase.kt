package com.wanda0002.eventku.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wanda0002.eventku.dao.EventDao

@Database(entities = [Event::class], version = 3, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}