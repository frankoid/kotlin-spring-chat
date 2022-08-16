package com.example.kotlin.chat

import com.example.kotlin.chat.repository.ContentType
import com.example.kotlin.chat.repository.Message
import com.example.kotlin.chat.service.MessageInputVM
import com.example.kotlin.chat.service.MessageVM
import com.example.kotlin.chat.service.UserVM
import org.springframework.web.util.HtmlUtils
import java.net.URL

fun MessageInputVM.asDomainObject(contentType: ContentType = ContentType.PLAIN): Message = Message(
    content,
    contentType,
    sent,
    user.name,
    user.avatarImageLink.toString(),
    id
)

fun Message.asViewModel(): MessageVM = MessageVM(
    HtmlUtils.htmlEscape(content),
    UserVM(username, URL(userAvatarImageLink)),
    sent,
    id
)

fun List<Message>.mapToViewModel(): List<MessageVM> = map { it.asViewModel() }
