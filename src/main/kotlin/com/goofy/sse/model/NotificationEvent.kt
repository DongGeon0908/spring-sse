package com.goofy.sse.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class NotificationEventModel(
    val id: Long,
    val message: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul")
    val createdAt: ZonedDateTime
)
