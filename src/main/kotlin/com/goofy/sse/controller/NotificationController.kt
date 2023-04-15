package com.goofy.sse.controller

import com.goofy.sse.service.NotificationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(
    private val notificationService: NotificationService
) {
    @GetMapping(
        path = ["/api/v1/notifications"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun notifyV1() = notificationService.notifyV1()
}
