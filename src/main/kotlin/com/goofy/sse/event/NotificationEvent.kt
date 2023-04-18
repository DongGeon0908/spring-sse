package com.goofy.sse.event

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class NotificationEventProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun produce(event: NotificationEventModel) {
        applicationEventPublisher.publishEvent(event)
    }
}

@Component
class NotificationEventConsumer {
    @org.springframework.context.event.EventListener(classes = [NotificationEventModel::class])
    fun consume(model: NotificationEventModel) {

    }
}

data class NotificationEventModel(
    val id: UUID,
    val title: String,
    val content: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul")
    val createdAt: ZonedDateTime
)
