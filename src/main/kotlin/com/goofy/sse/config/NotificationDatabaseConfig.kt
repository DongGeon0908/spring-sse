package com.goofy.sse.config

import com.goofy.sse.repository.NotificationRepository
import com.goofy.sse.repository.NotificationRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotificationDatabaseConfig {
    @Bean
    fun notificationRepository(): NotificationRepository {
        return NotificationRepositoryImpl()
    }
}
