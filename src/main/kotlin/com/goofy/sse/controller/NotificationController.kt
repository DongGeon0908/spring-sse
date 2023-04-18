package com.goofy.sse.controller

import com.goofy.sse.service.NotificationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(
    private val notificationService: NotificationService
) {
    @PostMapping(
        path = ["/api/v1/notifications"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun testAdd() = notificationService.testAdd()

    @GetMapping(
        path = ["/api/v1/notifications/counts"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun count() = notificationService.count()

    @GetMapping(
        path = ["/api/v1/notifications"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun notifyV1() = notificationService.notifyV1()
}
