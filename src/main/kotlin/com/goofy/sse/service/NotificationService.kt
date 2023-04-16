package com.goofy.sse.service

import com.goofy.sse.model.NotificationEventModel
import com.goofy.sse.service.SseEmitterEvent.Companion.send
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.concurrent.Executors

@Service
class NotificationService {
    fun notifyV1(): SseEmitter {
        // url encoding 필요
        val emitter = SseEmitterEvent.generate(180000)
        val sseMvcExecutor = Executors.newSingleThreadExecutor()

        println(LocalDateTime.now())

        sseMvcExecutor.execute {
            try {
                var i = 0
                while (true) {
                    val data = NotificationEventModel(
                        id = i.toLong(),
                        message = "hello world",
                        createdAt = ZonedDateTime.now()
                    )

                    emitter.send(
                        id = i.toString(),
                        name = "sse event with SSE EMITTER",
                        reconnectTime = 10000,
                        comment = URLEncoder.encode("SSE를 활용한 이벤트 루프"),
                        data = data
                    )

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
