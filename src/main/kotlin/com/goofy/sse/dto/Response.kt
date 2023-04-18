package com.goofy.sse.dto

import com.goofy.sse.entity.Notification
import java.time.ZonedDateTime
import java.util.*

data class NotificationResponse(
    val id: UUID,
    val uid: Long,
    val title: String,
    val content: String,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime
) {
    companion object {
        fun from(notification: Notification): NotificationResponse {
            return NotificationResponse(
                id = notification.id,
                uid = notification.uid,
                title = notification.title,
                content = notification.content,
                createdAt = notification.createdAt,
                modifiedAt = notification.modifiedAt
            )
        }
    }
}
