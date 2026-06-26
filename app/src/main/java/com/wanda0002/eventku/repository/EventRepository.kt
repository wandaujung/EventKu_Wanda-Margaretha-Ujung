package com.wanda0002.eventku.repository

import com.wanda0002.eventku.dao.EventDao
import com.wanda0002.eventku.data.Event
import com.wanda0002.eventku.data.remote.ApiClient

class EventRepository(
    private val dao: EventDao
) {

    val allEvents = dao.getAllEvents()

    suspend fun insert(event: Event) {
        dao.insertEvent(event)
    }

    suspend fun update(event: Event) {
        dao.updateEvent(event)
    }

    suspend fun delete(event: Event) {
        dao.deleteEvent(event)
    }

    suspend fun getById(id: Int): Event? {
        return dao.getEventById(id)
    }

    suspend fun getApiEvents() =
        ApiClient.eventApi.getEvents()

    suspend fun getEventCount(): Int {
        return dao.getEventCount()
    }

    suspend fun syncApiToRoom() {

        val count =
            dao.getEventCount()

        if (count > 0) {

            return
        }

        val apiEvents =
            ApiClient.eventApi.getEvents()

        val roomEvents =
            apiEvents.map {

                Event(
                    title = it.title,
                    description = it.body,
                    date = "API Event",
                    location = "Online",
                    imageUri = "",
                    isSynced = true,
                    source = "API"
                )
            }

        dao.insertEvents(roomEvents)
    }

    suspend fun syncPendingEvents() {

        val pendingEvents =
            dao.getPendingEvents()

        pendingEvents.forEach { event ->

            try {

                /*
                 kirim ke API
                 */

                dao.update(
                    event.copy(
                        isSynced = true
                    )
                )

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}