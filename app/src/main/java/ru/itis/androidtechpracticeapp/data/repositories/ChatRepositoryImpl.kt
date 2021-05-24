package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.MyApi
import ru.itis.androidtechpracticeapp.data.api.responses.ChatResponse
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

class ChatRepositoryImpl(
    private val myApi: MyApi
) : ChatRepository {
    override suspend fun getAllUserChats(userId: Int): List<ChatPresentation> {
        val response = myApi.getUserChats(userId)
        val result: List<ChatPresentation> = ArrayList()

        for (resp: ChatResponse in response) {
            (result as ArrayList).add(
                ChatPresentation(
                    resp.id,
                    resp.title,
                    resp.chatType,
                    MessagePresentation.from(resp.lastMessage)
                )
            )
        }

        return result
    }

    override suspend fun getChatCorrespondence(chatId: Int): List<MessagePresentation> {
        val response = myApi.getChatCorrespondence(chatId)

        return MessagePresentation.fromList(response)
    }
}