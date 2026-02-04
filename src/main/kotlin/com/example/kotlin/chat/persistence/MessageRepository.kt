package com.example.kotlin.chat.persistence

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface MessageRepository : CrudRepository<Message, String> {

    @Query("""
        SELECT *
        FROM (
            SELECT * 
            FROM messages
            ORDER BY "sent" DESC
            LIMIT 10
        )
        ORDER BY "sent"
    """)
    fun findLatest(): List<Message>


    @Query("""
        SELECT *
        FROM (SELECT *
              FROM messages
              WHERE sent > (SELECT sent FROM messages WHERE id = :id)
              ORDER BY sent DESC)
        ORDER BY sent
    """)
    fun findLatest(@Param("id") id: String): List<Message>
}