package com.goofy.sse.service

import com.goofy.sse.common.utis.ThreadManagerUtilsOrigin.Companion.generateExecutor
import com.goofy.sse.event.SseEmitterEvent
import com.goofy.sse.event.SseEmitterEvent.Companion.send
import com.goofy.sse.model.NotificationEventModel
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.net.URLEncoder
import java.time.ZonedDateTime

@Service
class NotificationService {
    private val logger = KotlinLogging.logger {}

    fun notifyV1(): SseEmitter {
        val emitter = SseEmitterEvent.generate(180000)

        // 가능하면, 코루틴으로
        val sseExecutor = generateExecutor(threadNamePrefix = "sse-emitter", poolSize = 1)

        sseExecutor.execute {
            var i = 0
            while (i != -1) {
                i++
                runCatching {
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
                }.onFailure {
                    logger.error { "error / ${it.message}" }
                    emitter.completeWithError(it)

                    // break
                    i = -1
                }
            }
        }

        sseExecutor.shutdown()

        return emitter
    }
}
