package com.goofy.sse.service

import com.goofy.sse.model.NotificationEventModel
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.ZonedDateTime
import java.util.concurrent.Executors

@Service
class NotificationService {
    fun notifyV1(): SseEmitter {
        val emitter = SseEmitter()
        val sseMvcExecutor = Executors.newSingleThreadExecutor()

        sseMvcExecutor.execute {
            try {
                var i = 0
                while (true) {
                    val event = SseEmitter.event()
                        .id(i.toString())
                        .name("sse event with SSE EMITTER")
                        .reconnectTime(10000)
                        .comment("SSE를 활용한 이벤트 루프")
                        .data(
                            NotificationEventModel(
                                id = i.toLong(),
                                message = "hello world",
                                createdAt = ZonedDateTime.now()
                            )
                        )

                    emitter.send(event)

                    Thread.sleep(1000)

                    i++
                }
            } catch (e: Exception) {
                emitter.completeWithError(e)
            }
        }

        return emitter
    }
}
