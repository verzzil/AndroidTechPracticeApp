package ru.itis.androidtechpracticeapp.presentation.models

import ru.itis.androidtechpracticeapp.data.api.responses.MessageResponse

data class MessagePresentation(
    val text: String?,
    val sendDate: Long?,
    val chatId: Int?,
    val userId: Int?,
    val userName: String?
) {

    companion object {
        fun clone(message: MessagePresentation): MessagePresentation =
            MessagePresentation(
                message.text,
                message.sendDate,
                message.chatId,
                message.userId,
                message.userName
            )

        fun from(message: MessageResponse): MessagePresentation =
            MessagePresentation(
                message.text,
                message.sendDate,
                message.chatId,
                message.userId,
                message.userName
            )

        fun fromList(messages: List<MessageResponse>): List<MessagePresentation> =
            messages.map(::from)
    }

}
