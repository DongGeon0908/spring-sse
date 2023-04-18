package com.goofy.sse.repository

import com.goofy.sse.entity.Notification
import java.util.*

class NotificationRepositoryImpl : NotificationRepository {
    private val NOTIFICATION_MEMORY = mutableListOf<Notification>()

    override fun save(notification: Notification): Notification {
        NOTIFICATION_MEMORY.add(notification)
        return notification
    }

    override fun findByIdOrNull(id: UUID): Notification? {
        return NOTIFICATION_MEMORY.firstOrNull { it.id == id }
    }

    override fun count(): Int {
        return NOTIFICATION_MEMORY.size
    }
}
