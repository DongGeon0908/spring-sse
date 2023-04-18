package com.goofy.sse.controller

import com.goofy.sse.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter
import java.util.concurrent.Executors


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

    @GetMapping(
        path = ["/api/v2/notifications"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun notifyV2() = notificationService.notifyV2()

    @GetMapping(
        path = ["/api/v3/notifications"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun notifyV3() = notificationService.notifyV3()

    private val executor = Executors.newCachedThreadPool()

    @GetMapping(
        path = ["/api/v4/notifications"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun handleRbe(): ResponseEntity<ResponseBodyEmitter> {
        val response = notificationService.notifyV4()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
