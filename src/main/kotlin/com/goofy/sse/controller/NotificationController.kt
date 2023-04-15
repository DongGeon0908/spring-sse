package com.goofy.sse.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalTime
import java.util.concurrent.Executors


@RestController
@RequestMapping("/api/v1/notifications")
class NotificationController {
    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun notify(): SseEmitter {
        val emitter = SseEmitter()
        val sseMvcExecutor = Executors.newSingleThreadExecutor()

        sseMvcExecutor.execute {
            try {
                var i = 0
                while (true) {
                    val event = SseEmitter.event()
                        .data("SSE MVC - " + LocalTime.now().toString())
                        .id(i.toString())
                        .name("sse event - mvc")

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
