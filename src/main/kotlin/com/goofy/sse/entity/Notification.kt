package com.goofy.sse.entity

import java.time.ZonedDateTime
import java.util.*

class Notification(
    val id: UUID = UUID.randomUUID(),

    val uid: Long,

    val title: String,

    val content: String,

    val createdAt: ZonedDateTime,

    val modifiedAt: ZonedDateTime
)
