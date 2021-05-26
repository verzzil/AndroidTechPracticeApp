package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

interface ChatRepository {

    suspend fun getAllUserChats(userId: Int): List<ChatPresentation>

    suspend fun getChatCorrespondence(chatId: Int): List<MessagePresentation>

    suspend fun getTitleDialog(chatId: Int, userId: Int): String

    suspend fun createChat(myId: Int, anotherUserId: Int): Int

}