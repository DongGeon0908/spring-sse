package com.goofy.sse.event

import org.springframework.http.MediaType
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

class SseEmitterEvent {
    companion object {
        fun generate(timeout: Long? = null): SseEmitter {
            return when (timeout) {
                null -> SseEmitter()
                else -> SseEmitter(timeout)
            }
        }

        fun SseEmitter.send(
            id: String? = null,
            name: String? = null,
            reconnectTime: Long? = null,
            comment: String? = null,
            data: Any? = null,
            mediaType: MediaType = MediaType.APPLICATION_JSON
        ) {
            val event = SseEmitter.event()
                .apply {
                    id?.let { this.id(id) }
                    name?.let { this.name(name) }
                    reconnectTime?.let { this.reconnectTime(reconnectTime) }
                    comment?.let { this.comment(comment) }
                    data?.let { this.data(data, mediaType) }
                }

            this.send(event)
        }
    }
}
