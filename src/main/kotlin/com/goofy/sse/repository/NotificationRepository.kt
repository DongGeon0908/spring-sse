package com.goofy.sse.repository

import com.goofy.sse.entity.Notification
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotificationRepository {
    fun save(notification: Notification): Notification

    fun findByIdOrNull(id: UUID): Notification?

    fun count(): Int
}
