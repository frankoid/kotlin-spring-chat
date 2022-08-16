package com.example.kotlin.chat.service

import java.net.URL
import java.time.Instant

data class MessageInputVM(val content: String, val user: UserVM, val sent: Instant, val id: String? = null)

data class MessageVM(val contentHtml: String, val user: UserVM, val sent: Instant, val id: String? = null)

data class UserVM(val name: String, val avatarImageLink: URL)
