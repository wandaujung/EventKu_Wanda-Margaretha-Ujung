package com.wanda0002.eventku.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wanda0002.eventku.data.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM events ORDER BY id DESC")
    fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT COUNT(*) FROM events")
    suspend fun getEventCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: Event)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<Event>)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM events WHERE id = :id")
    suspend fun getEventById(id: Int): Event?

    @Query("SELECT * FROM events WHERE isSynced= 0")
    suspend fun getPendingEvents(): List<Event>

    @Update
    suspend fun update(event: Event)
}