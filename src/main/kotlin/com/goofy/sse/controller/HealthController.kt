package com.goofy.sse.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/", "/health"])
class HealthController {
    @GetMapping
    fun health() = "health good"
}
