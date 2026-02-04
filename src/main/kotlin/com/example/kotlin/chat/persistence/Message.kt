package com.example.kotlin.chat.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("messages")
data class Message(
    val content: String,
    val contentType: ContentType,
    val sent: java.time.Instant,
    val username: String,
    val userAvatarImageLink: String,
    @Id var id: String? = null
)

enum class ContentType {
    PLAIN
}