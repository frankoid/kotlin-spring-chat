package com.example.kotlin.chat.controller

import com.example.kotlin.chat.service.MessageInputVM
import com.example.kotlin.chat.service.MessageService
import com.example.kotlin.chat.service.MessageVM
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/messages")
class MessageResource(val messageService: MessageService) {

    @GetMapping
    suspend fun latest(
        @RequestParam(
            value = "lastMessageId",
            defaultValue = ""
        ) lastMessageId: String
    ): ResponseEntity<List<MessageVM>> {
        val messages = if (lastMessageId.isNotEmpty()) {
            messageService.after(lastMessageId)
        } else {
            messageService.latest()
        }

        return if (messages.isEmpty()) {
            with(ResponseEntity.noContent()) {
                header("lastMessageId", lastMessageId)
                build<List<MessageVM>>()
            }
        } else {
            with(ResponseEntity.ok()) {
                header("lastMessageId", messages.last().id)
                body(messages)
            }
        }
    }

    @PostMapping
    suspend fun post(@RequestBody message: MessageInputVM) {
        messageService.post(message)
    }
}
